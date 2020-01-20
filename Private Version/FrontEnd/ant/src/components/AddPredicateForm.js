import React, { useState, useEffect } from "react";
import Axios from 'axios';
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import SaveIcon from "@material-ui/icons/Save";
import Button from "@material-ui/core/Button";
import DeleteIcon from "@material-ui/icons/Delete";
import PropTypes from 'prop-types';

//TODO verificar los requeridos

const useStyles = makeStyles(theme => ({
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
  }
}));

export default function LayoutTextFields() {
  const classes = useStyles();

  const initState = {
    name: "",
    description: "",
    code: "",
    activated: "true"
  };

  const [inputs, setInputs] = useState({initState});


  const handleChange = e => {
    const { name, value } = e.target;
    setInputs({ ...inputs, [name]: value });
  };

  const handleCleaner = e => {setInputs(initState) }

  const handleSubmit = e => {
    let url = 'http://localhost:8080/soploon/api/predicates/';
    Axios.post(url, inputs)
      .then(response => {})
      .catch(response => {});
    handleCleaner();
  };
  return (
    <div>
    <form className={classes.root} noValidate autoComplete="off">
      <TextField
        id="name"
        label="Nombre"
        name="name"
        value={inputs.name}
        onChange={handleChange}
        style={{ margin: 8 }}
        placeholder="Ej: Sublista de una lista "
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
        style={{ margin: 8 }}
        placeholder="Ej: ejemplo?"
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
        style={{ margin: 8 }}
        name="code"
        value={inputs.code}
        onChange={handleChange}
        placeholder="Ej: 	sublist(L,SUB):- append(AUX,_,L), append(_,SUB,AUX)."
        fullWidth
        required
        margin="dense"
        multiline={true}
        rows={6}
        rowsMax={8}
        InputLabelProps={{
          shrink: true
        }}
      />
      <Button
      type="submit"
        variant="contained"
        color="primary"
        className={classes.button}
        startIcon={<SaveIcon />}
        onClick={handleSubmit}
      >
        Guardar
      </Button>
      <Button
        variant="contained"
        color="primary"
        className={classes.button}
        startIcon={<DeleteIcon />}
        onClick={handleCleaner}
      >
        Borrar
      </Button>
      </form>


    </div>
  );
}
