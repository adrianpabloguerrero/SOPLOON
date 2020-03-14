import React, { useEffect} from 'react';
import { createStyles, makeStyles, Theme, withStyles } from '@material-ui/core/styles';
import { orange } from '@material-ui/core/colors';
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
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import {CustomCheckbox} from './utils/CustomCheckbox';
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

export default function Predicates() {

  const [open, setOpen] = React.useState(false);
  const [oldData, setOldData] = React.useState(
    { id: '',
      version: '',
      name: '',
      description: '',
      code:'',
      activated:''
    });

  const classes = useStyles();
  const handleClickOpen = (event,rowData) => {
    setOpen(true);
    if (rowData !== undefined){
      setInputs(rowData.selected);
      setOldData(rowData.selected);
    }
  };

  const handleActivatedChange = (event,predicate) =>{
    var predicadoSeleccionado = predicate.selected;
    var newValue = !predicadoSeleccionado.activated;
    predicadoSeleccionado.activated = newValue;

    let url = 'http://localhost:8080/soploon/api/predicates/'+predicadoSeleccionado.id;
    Axios.put(url, predicadoSeleccionado)
      .then(response => {
        const data = [...entries.data];
        const position = buscarIndice(data,predicate.selected);
        data[position].versions.forEach( el => { if (el.version !== predicadoSeleccionado.version) {el.activated = false} else {el.activated = response.data.activated}});
        setEntries({ data });
      })
      .catch(response => {});
  }
  const handleVersionChange = (event, predicate) => {
    const data = [...entries.data];
		const position = data.indexOf(predicate);
		var selectedVersion = event.target.value-1;
		data[position].selected = data[position].versions[selectedVersion];
		setEntries({data});
  }

  const handleClose = () => {
    setOpen(false);
    handleCleaner();
  };

   const buscarIndice = (predicateList, predicate) => {
		if (predicateList.length === 0)
			return -1;

		var index = 0;

		while (index < predicateList.length && predicate.id !== predicateList[index].selected.id)
			index++;

		if (index >= predicateList.length)
			return -1;

		return index;
   }

  const guardarEditar = () => {
    let url = 'http://localhost:8080/soploon/api/predicates/'+inputs.id;
    Axios.post(url, inputs)
      .then(response => {
        const data = [...entries.data];
        const position = buscarIndice(data,oldData);
        data[position].selected = response.data;
		    data[position].versions.forEach( el => {el.activated = false});
		    data[position].versions.push(response.data);
        setEntries({ data });
        handleClose();
      })
      .catch(response => {});

  }

  const guardarNuevoPredicado = () => {
    let url = 'http://localhost:8080/soploon/api/predicates/';
    Axios.post(url, inputs)
      .then(response => {
		var predicateObject = {};
		predicateObject.versions = [];
		predicateObject.versions.push(response.data);
		predicateObject.selected = response.data;
    const data = [...entries.data,predicateObject];
		setEntries({ data });
    handleClose();
      })
      .catch(response => {
	  });
  }

  const guardar = (oldData) => {
    if (inputs.id !== undefined)
      guardarEditar();
    else {
      guardarNuevoPredicado();
    }
  }

  //Regla
  const [entries, setEntries] = React.useState({
     data: [],
   });

   const initState = {
     name: '',
     description: '',
     code: '',
     activated: 'true',
   };
   //Entradas formulario
   const [inputs, setInputs] = React.useState({
     name: '',
     description: '',
     code: '',
     activated: 'true',
   });


   const handleChange = e => {
     const { name, value } = e.target;
     setInputs({ ...inputs, [name]: value });
   };
   
   const handleCleaner = e => {
      setInputs(initState);
      setOldData(initState);
    }
	const processData = data => {
		var predicates = {};

		data.forEach(predicateVersion => {
			if (predicates[predicateVersion.id] === undefined) {
				var predicate = {};
				predicate.versions = [];
				predicate.versions.push(predicateVersion);
				predicates[predicateVersion.id] = predicate;
			} else {
				predicates[predicateVersion.id].versions.push(predicateVersion);
			}
		});



		Object.entries(predicates).forEach(keyvalue => {
			var predicate = keyvalue[1];

			var index = 0;
			while (predicate.selected === undefined && index < predicate.versions.length) {
				if (predicate.versions[index].activated)
					predicate.selected = predicate.versions[index];
				index++;
			}

			if (predicate.selected === undefined)
				predicate.selected = predicate.versions[predicate.versions.length-1];

		});

		return predicates;
	}

	useEffect(() => {
		Axios
		.get('http://localhost:8080/soploon/api/predicates/')
		.then(response => {
			let data = [];
			var result = processData(response.data);
			Object.entries(result).forEach(keyvalue => {
				data.push(keyvalue[1]);
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
               emptyDataSourceMessage: 'No hay predicados para mostrar',
               deleteTooltip:'Eliminar',
               editTooltip:'Editar',
               filterRow: {
                   filterTooltip: 'Filtrar'
               },
               editRow: {
                 saveTooltip: 'Guardar',
                 cancelTooltip: 'Cancelar',
                 deleteText: '¿Está seguro que desea eliminar el predicado?'
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
            tooltip: 'Agregar predicado',
            isFreeAction: true,
            onClick: (event) => handleClickOpen(event)
          },

        ]}
      title="Predicados"
      columns={[
			 { title: 'Nombre', field: 'selected.name' },
			 { title: 'Descripción', field: 'selected.description' },
			 { title: 'Version', field: 'selected.version', render: predicate =>
				<Select onChange={(event) => handleVersionChange(event,predicate)} labelId="label" id="select" value={predicate.selected.version} disabled={predicate.versions.length === 1}>
					{predicate.versions.map((version,index) =>
					  <MenuItem key={version.version} value={version.version}>{version.version}</MenuItem>
					)}
				</Select>
			 },
			 { title: 'Activo', field: 'selected.activated', render: predicate =>
				<CustomCheckbox checked={predicate.selected.activated} onChange={(event) => handleActivatedChange(event,predicate)} value="activatedPredicate"/>
			 }
		]}
      data={entries.data}
    />
    <Dialog fullScreen open={open} onClose={handleClose} >
    <form className={classes.root} autoComplete="off">
    <AppBar className={classes.appBar}>
      <Toolbar>
        <IconButton edge="start" color="inherit" onClick={handleClose} aria-label="close">
          <CloseIcon />
        </IconButton>
        <Typography variant="h6" className={classes.title}>
          Edicion de predicados
        </Typography>
        <img src={Soploon} alt="Soploon" className={classes.image} / >
        <Button style={{ marginLeft: "auto", float: "right" }} onClick={guardar} autoFocus color="inherit">
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
        placeholder="Ej: Interfaces superiores"
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
        placeholder="Ej: Devuelve una lista con todas las interfaces superiores"
        fullWidth
        required
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
        placeholder="Ej: list_super_interfaces(ID,[]):-(parameterized_type(ID, _, _);..."
        fullWidth
        required
        margin="dense"
        multiline={true}
        rows={20}
        rowsMax={20}
        InputLabelProps={{
          shrink: true
        }}
      />
      </form>
    </Dialog>
</div>
  );
}
