import React, { useEffect } from 'react';
import { Theme, createStyles, withStyles, makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import TextField from "@material-ui/core/TextField";
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import Select from '@material-ui/core/Select';
import Button from '@material-ui/core/Button';
import { orange } from '@material-ui/core/colors';
import Axios from 'axios';
import MaterialTable from 'material-table';
import * as moment from 'moment'

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    root: {
     flexGrow: 1,
     height: "100%",
     width: '100%',
   },
   paper: {
     padding: theme.spacing(2),
     color: theme.palette.text.secondary,
     width: '100%',
   },
   form: {
     display: 'flex',
     flexDirection:"row",
     justifyContent: 'space-between',
   },
   select: {
     width:'100%',
   }
  }),
);

export default function Erros() {
  const classes = useStyles();
  const ColorButton = withStyles((theme: Theme) => ({
  root: {
    color: 'white',
    backgroundColor: orange[400],
    '&:hover': {
      backgroundColor: orange[600],
    },
  },
}))(Button);
const [users, setUsers] = React.useState({
   data: [],
 });

 const dateToDefault = () => {return moment(new Date()).format('YYYY-MM-DD')}
 const dateFromDefault = () => { return moment(dateToDefault()).subtract(1, 'months').format('YYYY-MM-DD')}

 const [errors, setErrors] = React.useState({
   data:[]
 });
 const [inputsSearch, setInputsSearch] = React.useState(
   {
     dateFrom: dateFromDefault() ,
     dateTo: dateToDefault(),
   });


 const [searchFiltrado, setSearchFiltrado] = React.useState('');

 const handleSearchFiltradoChange = (event: React.ChangeEvent<{ value: unknown }>) => {
   setSearchFiltrado(event.target.value);
 };

 const handleInputsChange = e => {
   const { name, value } = e.target;
   setInputsSearch({ ...inputsSearch, [name]: value });
 };

 const search = () => {
   const params = {
     date_start: new Date (inputsSearch.dateFrom).getTime()/1000,
     date_end: new Date (inputsSearch.dateTo).getTime()/1000,
   }
   Axios
   .get('http://localhost:8080/soploon/api/errors/',{params})
   .then(response => {
     let data = [];
     Object.entries(response.data).forEach(keyvalue => {
       data.push(keyvalue[1]);
     });
     setErrors ({ data: data });
   })
   .catch(function(error) {
     console.log(error);
   });
 }



useEffect(() => {
  Axios
  .get('http://localhost:8080/soploon/api/users/')
  .then(response => {
    let data = [];
    Object.entries(response.data).forEach(keyvalue => {
      data.push(keyvalue[1]);
    });
    setUsers ({ data: data });
  })
  .catch(function(error) {
    console.log(error);
  });

}, []);


    return (
      <div className={classes.root}>
      <Grid container style={{height: "100%"}}  alignItems="stretch"spacing={3}>
        <Grid item xs={5}>
          <Paper className={classes.paper}>
          <Grid container style={{height: "100%"}}  alignItems="stretch"spacing={3}>
              <Grid item xs={12}>

              <form  className={classes.form} noValidate autoComplete="off">
                <TextField
                  id="dateFrom"
                  label="Desde"
                  type="date"
                  name="dateFrom"
                  value={inputsSearch.dateFrom}
                  onChange={handleInputsChange}
                  InputLabelProps={{
                  shrink: true,
                  }}
                />
                <TextField
                  id="dateTo"
                  label="Hasta"
                  type="date"
                  name="dateTo"
                  value={inputsSearch.dateTo}
                  onChange={handleInputsChange}
                  InputLabelProps={{
                    shrink: true,
                  }}
                />
                </form>

                </Grid>
            <Grid item xs={12}>
            <div id="filtradoInput">
          <InputLabel shrink id="demo-simple-select-placeholder-label-label" style={{textAlign:"start"}}>
            Filtrado
          </InputLabel>
          <Select
            labelId="demo-simple-select-placeholder-label-label"
            id="demo-simple-select-placeholder-label"
            displayEmpty
            className={classes.select}
            value={searchFiltrado}
            onChange={handleSearchFiltradoChange}
          >
            <MenuItem value="">
              <em>Todos</em>
            </MenuItem>
            <MenuItem value="usuarios">
              <em>Usuarios</em>
            </MenuItem>
            <MenuItem value="proyectos">
              <em>Proyectos</em>
            </MenuItem>
          </Select>
        </div>
        </Grid>
        <Grid item xs={12}>

        <ColorButton onClick= {search}variant="contained" color="primary" className={classes.margin}>
          Buscar
        </ColorButton>
        </Grid>
          </Grid>
          </Paper>
        </Grid>
        <Grid item xs={7}>
          <Paper className={classes.paper}>
          <MaterialTable
          localization={{
                 pagination: {
                     labelDisplayedRows: '{from}-{to} de {count}',
                     labelRowsPerPage:'Filas por página',
                     labelRowsSelect: 'Filas',
                     firstAriaLabel: 'Primera página',
                     firstTooltip:'Primera página',
                     previousAriaLabel: 'Página anterior',
                     previousTooltip: 'Página anterior',
                     nextAriaLabel: 'Próxima página',
                     nextTooltip: 'Próxima página',
                     lastAriaLabel: 'Última página',
                     lastTooltip: 'Última página'
                 },
                 toolbar: {
                     nRowsSelected: '{0} filas(s) seleccionadas',
                     searchTooltip: 'Buscar',
                     searchPlaceholder: 'Buscar'
                 },
                 header: {
                     actions: 'Acciones'
                 },
                 body: {
                     emptyDataSourceMessage: 'No hay datos para mostrar',

                     editRow: {
                       saveTooltip: 'Guardar',
                       cancelTooltip: 'Cancelar',
                       deleteText: '¿Está seguro que lo desea eliminar?'
                     }
                 }
             }}

            title="Errores"
            columns={[
             { title: 'Nombre', field: 'selected.name' },
             { title: 'Descripción', field: 'selected.description' },
          ]}
            data={users.data}
          />
          </Paper>
        </Grid>
      </Grid>
    </div>
    );

}
