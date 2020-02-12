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
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Checkbox from '@material-ui/core/Checkbox';

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
const [users, setUsers] = React.useState({});
const [projects, setProjects] = React.useState({});
const [dataTableFilter, setDataTableFilter] = React.useState([]);

const CustomCheckbox = withStyles({
root: {
 color: orange[400],
 '&$checked': {
   color: orange[600],
 },
},
checked: {},
})(props => <Checkbox color="default" {...props} />);

 const dateToDefault = () => {return moment(new Date()).utcOffset('GMT-03:00').format('YYYY-MM-DD')}
 const dateFromDefault = () => { return moment(dateToDefault()).subtract(1, 'months').format('YYYY-MM-DD')}

 const [errors, setErrors] = React.useState([]);
 const [errorsTable, setErrorsTable] = React.useState([]);
 const [inputsSearch, setInputsSearch] = React.useState(
   {
     dateFrom: dateFromDefault() ,
     dateTo: dateToDefault(),
   });

 const [searchFiltrado, setSearchFiltrado] = React.useState('');

 const handleSearchFiltradoChange = (event: React.ChangeEvent<{ value: unknown }>) => {
   setSearchFiltrado(event.target.value);
   if (event.target.value === 'usuarios')
    setDataTableFilter(users);
   if (event.target.value === 'proyectos')
    setDataTableFilter(projects);
};



 const handleInputsChange = e => {
   const { name, value } = e.target;
   setInputsSearch({ ...inputsSearch, [name]: value });
   loadCompleteErrors();
   setSearchFiltrado('');
 };

 const getUsers = data => {
   const result = [];
   const map = new Map();
   for (const item of data ) {
     if(!map.has(item.userId)){
        map.set(item.userId, true)    // set any value to Map
        result.push({
            id: item.userId,
            name: item.nameUser,
            selected: false
        })
      }
    }
    return result;
 }

 const getProjects = data => {
   const result = [];
   const map = new Map();
   for (const item of data ) {
     if(!map.has(item.projectId)){
        map.set(item.projectId, true)    // set any value to Map
        result.push({
            id: item.projectId,
            name: item.nameProject,
            selected: false
        })
      }
    }
    return result;
 }

 const loadCompleteErrors = () => {
   const params = {
     date_start: moment(inputsSearch.dateFrom).unix()*1000,
     date_end: moment(inputsSearch.dateTo).add('days',1).subtract('second',1).unix()*1000,
   }

   let data = [];
   Axios
   .get('http://localhost:8080/soploon/api/errors/',{params})
   .then(response => {
     Object.entries(response.data).forEach(keyvalue => {
       data.push(keyvalue[1]);
     });
     setErrors (data);
     const usuarios = getUsers(data);
     const projects = getProjects(data);
     setUsers (usuarios);
     setProjects (projects);
   })
   .catch(function(error) {
     console.log(error);
   });
    let users = [];
 }

 const handleSelectedChange = (item) =>{
   const data = [...dataTableFilter];
   const position = data.indexOf(item);
   var newValue = !item.selected;
   item.selected = newValue;
   setDataTableFilter(data);
 }

 const handleReviewedChange = (e,error) => {

   //Actualizo la lista de errores general
   const errorsAux = [...errors];
   let position = errorsAux.indexOf(error);
   errorsAux[position].reviewed = e.target.value;
   setErrors(errorsAux);

   //Actualizo la tabla
   const errorsTableAux = [...errorsTable];
   position = errorsTableAux.indexOf(error);
   errorsTableAux[position].reviewed = e.target.value;
   setErrorsTable(errorsTableAux);

console.log(errors);
   const path = {
     id:error.id,
     userId: error.userId,
     projectId: error.projectId,
     date: error.date
   }

   const modifiedError = {
     id:error.id,
     userId: error.userId,
     projectId: error.projectId,
     date: error.date/1000,
     ruleId: error.ruleId,
     versionRule: error.versionRule,
     codeLocation: error.codeLocation,
     representationLocation: error.representationLocation,
     reviewed: e.target.value
   }

   let url = 'http://localhost:8080/soploon/api/users/'+path.userId+'/projects/'+path.projectId+'/corrections/'+path.date+'/errors/'+path.id;
   console.log(url);
   Axios.put(url, modifiedError)
     .then(response => {
       console.log("then")
       //const data = [...entries.data];
       //const position = buscarIndice(data,rule.selected);
       //data[position].versions.forEach( el => { if (el.version !== reglaSeleccionada.version) {el.activated = false} else {el.activated = response.data.activated}});
       //setEntries({ data });
     })
     .catch(response => {});



  }

 const search = () => {
  if (searchFiltrado==='')
    setErrorsTable(errors);
  if (searchFiltrado==='usuarios'){
    const data = [];
    users.forEach((user, i) => {
      if (user.selected)
        errors.forEach((error, i) => {
          if (error.userId === user.id)
            data.push(error);
        });
    });
    setErrorsTable(data);
  }
  if (searchFiltrado==='proyectos'){
    const data = [];
    projects.forEach((project, i) => {
      if (project.selected)
        errors.forEach((error, i) => {
          if (error.projectId === project.id)
            data.push(error);
        });
    });
    setErrorsTable(data);
  }

 }

useEffect(() => {
loadCompleteErrors();
}, []);

    return (
      <div className={classes.root}>
      <Grid container style={{height: "100%"}}  alignItems="stretch"spacing={3}>
        <Grid item xs={4}>
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
                  style={{width: "45%"}}
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
                  style={{width: "45%"}}
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
        { searchFiltrado !== "" ?
        <Grid item xs={12}>
        <TableContainer component={Paper}>
             <Table className={classes.table} aria-label="simple table">
               <TableHead>
                 <TableRow>
                   <TableCell>Nombre</TableCell>
                   <TableCell align="center">Seleccionar</TableCell>
                 </TableRow>
               </TableHead>
               <TableBody>
               { dataTableFilter.length > 0 ? dataTableFilter.map(item => (
                  <TableRow key={item.name}>
                  <TableCell component="th" scope="row">
                  {item.name}
                  </TableCell>
                  <TableCell align="center" >
                  <CustomCheckbox checked={item.selected} onChange={() => handleSelectedChange (item)}/>
                </TableCell>

        </TableRow>
      )):null}
               </TableBody>
             </Table>
           </TableContainer>
        </Grid> : null }
        <Grid item xs={12}>
        <ColorButton onClick= {search}variant="contained" color="primary" className={classes.margin}>
          Buscar
        </ColorButton>
        </Grid>
          </Grid>
          </Paper>
        </Grid>
        <Grid item xs={8}>
          <MaterialTable
          localization={{
                 pagination: {
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
             { title: 'Usuario', field: 'nameUser' },
             { title: 'Regla', field: 'nameRule' },
             { title: 'Proyecto', field: 'nameProject' },
             { title: 'Estado', field: 'reviewed', render: error =>
             <Select labelId="label" name="reviewed" id="select" onChange={(event) => handleReviewedChange(event,error)} value={error.reviewed}>
                  <MenuItem key="0" value="0">No revisado</MenuItem>
                  <MenuItem key="1" value="1">Falso positivo</MenuItem>
                  <MenuItem key="2" value="2">Confirmado</MenuItem>
             </Select>
      			 }
          ]}
            data={errorsTable}
          />
          </Grid>
      </Grid>
    </div>
    );
}
