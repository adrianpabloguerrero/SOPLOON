import React from 'react';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import TextField from "@material-ui/core/TextField";
import InputLabel from '@material-ui/core/InputLabel';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import TableFilter from './TableFilter';
import {ColorButton} from '../utils/ColorButton';
import Axios from 'axios';
import * as moment from 'moment';

const dateToDefault = () => {return moment(new Date()).utcOffset('GMT-03:00').format('YYYY-MM-DD')};
const dateFromDefault = () => { return moment(dateToDefault()).subtract(1,'months').format('YYYY-MM-DD')};
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

class Filter extends React.Component {
  constructor(props) {
		super(props);
		this.state = {
      users: [],
      projects:[],
      searchFiltrado:"",
      dateFrom: dateFromDefault(),
      dateTo: dateToDefault(),
      dataTableFilterState: "",
    }

    this.loadCompleteErrors(dateFromDefault(),dateToDefault());
	}

  dataTableFilter = ({});
  errors = ({});
  loadCompleteErrors = (dateFrom,dateTo) => {
     const params = {
       date_start: moment(dateFrom).unix()*1000,
       date_end: moment(dateTo).add(1,'days').subtract(1,'second').unix()*1000,
     }
     let data = [];
     Axios
     .get('http://localhost:8080/soploon/api/errors',{params})
     .then(response => {
       Object.entries(response.data).forEach(keyvalue => {
         data.push(keyvalue[1]);
       });
       this.errors = data;
     })
     .catch(function(error) {
       console.log(error);
     });
   }


  handleSearchFiltradoChange = (event: React.ChangeEvent<{ value: unknown }>) => {
    this.setState({searchFiltrado:event.target.value})
    if (event.target.value === 'usuarios')
    this.dataTableFilter = getUsers(this.errors);
    if (event.target.value === 'proyectos')
      this.dataTableFilter = getProjects(this.errors);
    if (event.target.value === 'estado')
    this.dataTableFilter = [
      {id: 0, name: "No revisado", selected: false},
      {id: 1, name: "Falso positivo", selected: false},
      {id: 2, name: "Confirmado", selected: false},
    ]
};

  handleDatesChange = e => {
    this.setState({ [e.target.name]: e.target.value });
    if (e.target.name === "dateFrom") {
         this.loadCompleteErrors(e.target.value,this.state.dateTo);
       }
       else {
         this.loadCompleteErrors(this.state.dateFrom,e.target.value);
       }
    this.setState({searchFiltrado:""})
  }

  handleInputsChange = e => {
    this.setState({ [e.target.name]: e.target.value });
  };

  handleFilterClick =  (event,item) =>{
    let position = this.dataTableFilter.indexOf(item);
    this.dataTableFilter[position].selected = !item.selected;;
    this.setState({dataTableFilterState:this.dataTableFilter});
  }

  search = () => {
    let data = [];
     if (this.state.searchFiltrado===''){
       data = this.errors;
     }
     if (this.state.searchFiltrado==='usuarios'){
       this.dataTableFilter.forEach((user, i) => {
         if (user.selected)
           this.errors.forEach((error, i) => {
             if (error.userId === user.id)
               data.push(error);
           });
       });
     }
     if (this.state.searchFiltrado==='proyectos'){
       this.dataTableFilter.forEach((project, i) => {
         if (project.selected)
           this.errors.forEach((error, i) => {
             if ((error.projectId === project.id) && (error.userId === project.userId))
               data.push(error);
           });
       });
     }
     if (this.state.searchFiltrado==='estado'){
       this.dataTableFilter.forEach((estado, i) => {
         if (estado.selected)
         this.errors.forEach((error, i) => {
             if (error.reviewed === estado.id)
               data.push(error);
           });
       });
     }
     this.props.handleErrorsSelected(data);

}

  render() {
    const { style } = this.props;
    return (
    <Paper className={style.paperError}>
        <Typography variant="h6" component="h2" className={style.itemFilter} style={{margin:"10px 10px 10px 0px"}}>
         Filtrar
        </Typography>
        <form  className={style.itemFilterDate} noValidate autoComplete="off">
            <TextField
                id="dateFrom"
                label="Desde"
                type="date"
                name="dateFrom"
                value={this.state.dateFrom}
                onChange={this.handleDatesChange}
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
                value={this.state.dateTo}
                onChange={this.handleDatesChange}
                style={{width: "45%"}}
                InputLabelProps={{
                  shrink: true,
                }}
            />
          </form>
          <div id="filtradoInput" className={style.itemFilter}>
                <InputLabel shrink id="demo-simple-select-placeholder-label-label" style={{textAlign:"start"}}>
                  Filtrado
                </InputLabel>
                <Select
                  labelId="demo-simple-select-placeholder-label-label"
                  id="demo-simple-select-placeholder-label"
                  displayEmpty
                  name="searchFiltrado"
                  className={style.select}
                  value={this.state.searchFiltrado}
                  onChange={this.handleSearchFiltradoChange}
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
        { (this.state.searchFiltrado !== "") &&
          <div>
          <TableFilter style={{"marginTop":"100px"}} listItems={this.dataTableFilter} handleClick={this.handleFilterClick}/>
          </div>
         }
           <ColorButton style={{"marginTop": "auto"}} onClick={this.search} variant="contained" color="primary">
             Buscar
           </ColorButton>
    </Paper>)	}
}
export default Filter;
