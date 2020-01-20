import React, { useEffect, useState } from 'react';
import { createStyles, makeStyles, Theme } from '@material-ui/core/styles';
import MaterialTable from 'material-table';
import Button from "@material-ui/core/Button";
import Dialog from '@material-ui/core/Dialog';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import CloseIcon from '@material-ui/icons/Close';
import Soploon from '../images/soploon.png';
import TextField from "@material-ui/core/TextField";
import Axios from 'axios';


const useStyles = makeStyles((theme: Theme) =>

  createStyles({
    appBar: {
      position: 'relative',
      backgroundColor:"#222",
    },
    image: {
      width:"30px",
      marginLeft:"10px"
    },
    root: {
      display: "flex",
      flexWrap: "wrap"
    },
    textField: {
      marginLeft: theme.spacing(5),
      marginRight: theme.spacing(5)
    },
    button: {
      margin: theme.spacing(1)
    },
    title: {
      marginLeft: theme.spacing(2),
      color:"#9d9d9d"
    },
  }),
);

export default function Rules() {

  const [open, setOpen] = React.useState(false);
  const [oldData, setOldData] = React.useState(
  {  id: '',
     version: '',
     name: '',
     description: '',
     link:'',
     query: '',
     code:'',
     activated:''
   });
  const classes = useStyles();
  const handleClickOpen = (event,rowData) => {
    setOpen(true);
    if (rowData != undefined){
      setInputs(rowData);
      setOldData(rowData);
    }
  };

  const handleClose = () => {
    setOpen(false);
    handleCleaner();
  };

  const guardarEditar = () => {
    let url = 'http://localhost:8080/soploon/api/rules/'+inputs.id;
    Axios.put(url, inputs)
      .then(response => {
        handleClose();
        const data = [...entries.data];
        const position = data.indexOf(oldData);
        data[position] = response.data;
        setEntries({ data });
        handleCleaner();
      })
      .catch(response => {});

  }

  const guardarNuevaRegla = () => {
    let url = 'http://localhost:8080/soploon/api/rules/';
    Axios.post(url, inputs)
      .then(response => {
        const data = [...entries.data,response.data];
        console.log(data);
        setEntries({ data });
        handleCleaner();
        handleClose();
      })
      .catch(response => {});
  }

  const guardar = (oldData) => {
    if (inputs.id != undefined)
      guardarEditar();
    else {
      guardarNuevaRegla();
    }
  }
  //Regla
   const [entries, setEntries] = React.useState({
     data: [
       { id: '',
         version: '',
         name: '',
         description: '',
         link:'',
         query: '',
         code:'',
         activated:''
       }
     ],
   });
  //Entradas tabla
  const [state] = React.useState({
    columns: [
     { title: 'Nombre', field: 'name' },
     { title: 'Description', field: 'description' },
     { title: 'Version', field: 'version' },
   ],
  });
   const initState = {
     id:'',
     name: '',
     description: '',
     link: '',
     query: '',
     code: '',
     activated: 'true',
   };
   //Entradas formulario
   const [inputs, setInputs] = React.useState({
     name: '',
     description: '',
     link: '',
     query: '',
     code: '',
     activated: 'true',
   });
   const [errors,setErrors] = React.useState({
     errorName: "",
   });
   const handleChange = e => {
     const { name, value } = e.target;
     setInputs({ ...inputs, [name]: value });
   };
   const handleCleaner = e => {
      setInputs(initState);
      setOldData(initState);
    }


  useEffect(() => {
         Axios
         .get('http://localhost:8080/soploon/api/rules/')
         .then(response => {
         let data = [];
         response.data.forEach(el => {
         data.push({
         id: el.id,
         version: el.version,
         name: el.name,
         description: el.description,
         link: el.link,
         query: el.query,
         code: el.code,
         activated: el.activated
     });
 });
     setEntries({ data: data });
 })
 .catch(function(error) {
         console.log(error);
     });
 }, []);
  return (
    <div>
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
               emptyDataSourceMessage: 'No hay reglas para mostrar',
               deleteTooltip:'Eliminar',
               editTooltip:'Editar',
               filterRow: {
                   filterTooltip: 'Filtrar'
               },
               editRow: {
                 saveTooltip: 'Guardar',
                 cancelTooltip: 'Cancelar',
                 deleteText: '¿Está seguro que desea eliminar la regla?'
               }
           }
       }}
       actions={[
          {
            icon: 'edit',
            tooltip: 'Editar',
            onClick: (event, rowData) => handleClickOpen(event,rowData)
          },
          {
            icon: 'add',
            tooltip: 'Agregar regla',
            isFreeAction: true,
            onClick: (event) => handleClickOpen(event)
          },

        ]}
      title="Reglas activas"
      columns={state.columns}
      data={entries.data}
    />
    <Dialog fullScreen open={open} onClose={handleClose} >
    <form className={classes.root} onSubmit={guardar} autoComplete="off">
    <AppBar className={classes.appBar}>
      <Toolbar>
        <IconButton edge="start" color="inherit" onClick={handleClose} aria-label="close">
          <CloseIcon />
        </IconButton>
        <Typography variant="h6" className={classes.title}>
          Edicion de reglas
        </Typography>
        <img src={Soploon} className={classes.image} / >
        <Button style={{ marginLeft: "auto", float: "right" }}
        autoFocus color="inherit" type="submit">
          Guardar
        </Button>
      </Toolbar>
    </AppBar>
      <TextField
        id="name"
        label="Nombre"
        name="name"
        value={inputs.name}
        onChange={handleChange}
        style={{ margin: 20 }}
        placeholder="Ej: Atributo público"
        fullWidth
        required
        margin="normal"
        InputLabelProps={{
          shrink: true
        }}
      />
      <TextField
        id="description"
        label="Descripción"
        name="description"
        value={inputs.description}
        onChange={handleChange}
        style={{ margin: 20 }}
        placeholder="Ej: Los atributos públicos pueden ser modificados desde el exterior..."
        fullWidth
        required
        margin="dense"
        InputLabelProps={{
          shrink: true
        }}
      />
      <TextField
        id="link"
        label="Link"
        name="link"
        value={inputs.link}
        onChange={handleChange}
        style={{ margin: 20 }}
        required
        placeholder="Ej: http://www.google.com.ar"
        fullWidth
        margin="dense"
        InputLabelProps={{
          shrink: true
        }}
      />
      <TextField
        id="query"
        label="Query"
        name="query"
        value={inputs.query}
        onChange={handleChange}
        style={{ margin: 20 }}
        required
        placeholder="Ej: public_attribute"
        fullWidth
        margin="dense"
        InputLabelProps={{
          shrink: true
        }}
      />
      <TextField
        id="code"
        label="Código"
        style={{ margin: 20 }}
        name="code"
        value={inputs.code}
        onChange={handleChange}
        placeholder="Ej: 	public_attribute(ID):- field_declaration(ID, _, _, _, _, _, _, compilation_unit(UNIT)), public_field(ID), not(final_field(ID)), not(static_field(ID)), model_unit(UNIT)."
        fullWidth
        required
        margin="dense"
        multiline={true}
        rows={10}
        rowsMax={10}
        InputLabelProps={{
          shrink: true
        }}
      />
      </form>
    </Dialog>
</div>
  );
}
