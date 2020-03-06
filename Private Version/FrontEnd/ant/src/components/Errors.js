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
import Typography from '@material-ui/core/Typography';
import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';
import BarChartIcon from '@material-ui/icons/BarChart';
import PieChartIcon from '@material-ui/icons/PieChart';
import TimelineIcon from '@material-ui/icons/Timeline';
import StorageIcon from '@material-ui/icons/Storage';
import Highcharts from 'highcharts';
import PieChart from 'highcharts-react-official';
import HighchartsReact from 'highcharts-react-official';
import exporting from "highcharts/modules/exporting";

exporting(Highcharts);

const useStyles = makeStyles((theme: Theme) =>
  createStyles({
    paperInfo: {
      height:500,
      display:'flex',
      alignItems:'center',
      justifyContent:'center'
    },
    container: {
      display:'flex',
      flexDirection:'column',
      maxHeight:"100%"
    },
    itemGrafico: {
      maxHeight:"40%",
    },

    root: {
     flexGrow: 1,
     minHeight: "120%",
     maxHeight: "120%",
     width: '100%',
   },
   paper: {
     padding: "0px 16px 16px 16px",
     color: theme.palette.text.secondary,
     width: '100%',
     minHeight:575,
     maxHeight: 575,
     overflow: 'auto'
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

var sort = null;

const [optionsChart,setOptionsChart] = React.useState ({title: {
  text: 'No hay datos para mostrar',
},});
const [dataPieChart,setDataPieChart] = React.useState({});
const [projects, setProjects] = React.useState({});
const [users, setUsers] = React.useState({});
const [dataTableFilter, setDataTableFilter] = React.useState([]);
const [valueNavBar, setValueNavBar] = React.useState(0);
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
const [estados, setEstados] = React.useState([
  {id: 0, name: "No revisado", selected: false},
  {id: 1, name: "Falso positivo", selected: false},
  {id: 2, name: "Confirmado", selected: false},
]);
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

 //Todos los errores
 const [errors, setErrors] = React.useState([]);
 //Errores visibles en la tabla
 const [errorsTable, setErrorsTable] = React.useState([]);
 //Valores de busqueda
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
   if (event.target.value === 'estado')
    setDataTableFilter(estados);
};

 const handleInputsChange = e => {
   const { name, value } = e.target;
   setInputsSearch({ ...inputsSearch, [name]: value });
   loadCompleteErrors();
   setSearchFiltrado('');
 };

 const getUsers = data => {
   //Devuelve un arreglo con los diferentes usuarios
   const result = [];
   const map = new Map();
   for (const item of data ) {
     if(!map.has(item.userId)){
        map.set(item.userId, true)
        result.push({
            id: item.userId,
            name: item.nameUser,
            selected: false
        })
      }
    }
    return result;
 }

 const getTypeErrorsPercent = errors => {
   const result = [];
   const map = new Map();
   for (const item of errors){
     if (!map.has(item.nameRule)){
       map.set(item.nameRule,1);
     }
     else {
       map.set(item.nameRule,map.get(item.nameRule)+1);
     }
   }
   map.forEach((item, i) => {
    result.push({name:i,y:(100*item/(errors.length))})
   });
   return result;
 }

 const getTypeErrorsNumber = errors => {
   const result = [];
   const map = new Map();
   for (const item of errors){
     if (!map.has(item.nameRule)){
       map.set(item.nameRule,1);
     }
     else {
       map.set(item.nameRule,map.get(item.nameRule)+1);
     }
   }
   map.forEach((item, i) => {
    result.push({name:i,y:item})
   });
   return result;
 }

 const getProjects = data => {
   const result = [];
   const map = new Map();
   for (const item of data ) {
     if(!map.has(item.projectId + " " + item.userId)){
        map.set(item.projectId + " " + item.userId, true)    // set any value to Map
        result.push({
            id: item.projectId,
            userId: item.userId,
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

   console.log(params);
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

 const handleSelectedChange = (e,item) =>{
   const dataTableFilterAux = [...dataTableFilter];
   let position = dataTableFilterAux.indexOf(item);
   const newValue = !item.selected;
   dataTableFilterAux[position].selected = newValue;
   setDataTableFilter(dataTableFilterAux);
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
     date: error.date,
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

     })
     .catch(response => {console.log(response);});
  }

 const processDataChart = (errors) => {
    setOptionsChart ({
      title: {
        text: 'Errores'
      },
      chart: {
      type: "pie"
    },
    exporting: {
             enabled: true
           },
    tooltip: {
        pointFormat: "Cantidad: {point.y:.2f} %"
    },
      series: [{
        name:"Cantidad",
        data: getTypeErrorsPercent(errors)
      }]
    })

 }

 const search = () => {
  if (searchFiltrado===''){
    setErrorsTable(errors);
  }
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
          if ((error.projectId === project.id) && (error.userId === project.userId))
            data.push(error);
        });
    });
    setErrorsTable(data);
  }
  if (searchFiltrado==='estado'){
    const data = [];
    estados.forEach((estado, i) => {
      if (estado.selected)
        errors.forEach((error, i) => {
          if (error.reviewed === estado.id)
            data.push(error);
        });
    });
    setErrorsTable(data);
  }
 }

 let nombreClases = (error) => {
   let out = "";
   error.codeLocation.forEach((item, i) => {
     if (out.length > 0){
       out += ", ";
    }
     let clase = item.path.substring(item.path.lastIndexOf("/")+1,item.path.length).substring();
     out += clase.substring(0,clase.indexOf("."));
   });
   return out;
 }

useEffect(() => {
loadCompleteErrors();
}, []);

    return (
      <div className={classes.root} >
      <Grid container alignItems="stretch"spacing={3}>
        <Grid item xs={4}>
          <Paper className={classes.paper}>
          <Typography variant="h6" component="h2" style={{display:"flex", margin:"10px 10px 10px 0px"}}>
         Filtrar
       </Typography>
          <Grid container alignItems="stretch"spacing={3}>
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
            <MenuItem value="estado">
              <em>Estado</em>
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
              </Table>
              <div style={{ overflow: 'auto', height: "250px" }}>
              <Table>
               <TableBody >
               { dataTableFilter.length > 0 ? dataTableFilter.map(item => (
                  <TableRow key={item.name}>
                  <TableCell component="th" scope="row">
                  {item.name}
                  </TableCell>
                  <TableCell align="center" >
                  <CustomCheckbox checked={item.selected} onChange={(event) => handleSelectedChange (event,item)}/>
                </TableCell>

        </TableRow>
      )):null}
               </TableBody>
             </Table>
             </div>
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
        <Grid item xs={8} className={classes.container}>
        <BottomNavigation style = {{marginBottom: "18px"}}
        showLabels
        value={valueNavBar}
        onChange={(event, newValue) => {
        setValueNavBar(newValue);
      }}
      showLabels
        >
        <BottomNavigationAction label="Lista" icon={<StorageIcon />} />
        <BottomNavigationAction label="Pie Chart" icon={<PieChartIcon />} />
        <BottomNavigationAction label="Bar Chart" icon={<BarChartIcon />}  />
        <BottomNavigationAction label="Time Line" icon={<TimelineIcon />}  />

        </BottomNavigation>
        { valueNavBar == 0 ?
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
             actions={[
                {
                  icon: 'search',
                  tooltip: 'Ver en el explorador',
                  onClick: () => console.log("ver error")
                }
              ]}
            title="Errores"
            columns={[
             { title: 'Usuario', field: 'nameUser' },
             { title: 'Regla', field: 'nameRule' },
             { title: 'Proyecto', field: 'nameProject' },
             { title: 'Clase', field:'nameclass', render: error => nombreClases(error)},
             { title: 'Estado', field: 'reviewed', render: error =>
             <Select labelId="label" name="reviewed" id="select" onChange={(event) => handleReviewedChange(event,error)} value={error.reviewed}>
                  <MenuItem key="0" value={0}>No revisado</MenuItem>
                  <MenuItem key="1" value={1}>Falso positivo</MenuItem>
                  <MenuItem key="2" value={2}>Confirmado</MenuItem>
             </Select>
      			 }
          ]}
            data={errorsTable}
          /> : null}
          { valueNavBar == 1 ?
          <Paper className={classes.paperInfo}>
          <PieChart
          highcharts={Highcharts}
          options={{
            title: {
              text: 'Porcentaje de errores'
            },
            chart: {
            type: "pie"
          },
          exporting: {
                   enabled: true
                 },
          tooltip: {
              pointFormat: "Cantidad: {point.y:.2f} %"
          },
            series: [{
              name:"Cantidad",
              data: getTypeErrorsPercent(errorsTable)
            }]
          }}
    />
          </Paper> : null }
          { valueNavBar == 2 ?
          <Paper className={classes.paperInfo}>
          <HighchartsReact
          highcharts={Highcharts}
          options={{
            title: {
              text: 'Cantidad de errores'
            },
            chart: {
              type: 'column'
            },
            xAxis: {
                type: 'category',
                labels: {
                    rotation: -45,
                    style: {
                        fontSize: '7px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                      min: 0,
                      title: {
                          text: 'Cantidad total de errores'
                      }
                  },
          legend: {
            enabled: false
        },
          exporting: {
                   enabled: true
                 },

            series: [{
              name:"Cantidad total",
              data: getTypeErrorsNumber(errorsTable)
            }]
          }}

    />
          </Paper> : null }

          { valueNavBar == 3 ?
          <Paper className={classes.paperInfo}>
          <HighchartsReact
          highcharts={Highcharts}
          options={{
            title: {
              text: 'Cantidad de errores'
            },
            chart: {
              type: 'column'
            },
            xAxis: {
                type: 'category',
                labels: {
                    rotation: -45,
                    style: {
                        fontSize: '7px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                      min: 0,
                      title: {
                          text: 'Cantidad total de errores'
                      }
                  },
          legend: {
            enabled: false
          },
          exporting: {
                   enabled: true
                 },

            series: [{
              name:"Cantidad total",
              data: getTypeErrorsNumber(errorsTable)
            }]
          }}

          />
          </Paper> : null }


          </Grid>
      </Grid>
    </div>
    );
}
