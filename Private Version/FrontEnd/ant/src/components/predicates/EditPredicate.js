import React from "react";
import Button from "@material-ui/core/Button";
import Dialog from "@material-ui/core/Dialog";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import IconButton from "@material-ui/core/IconButton";
import Typography from "@material-ui/core/Typography";
import CloseIcon from "@material-ui/icons/Close";
import Soploon from "../utils/images/soploon.png";
import TextField from "@material-ui/core/TextField";
import Style from "../utils/style";

export default function EditPredicate({
  handleClose,
  guardar,
  handleChange,
  inputs
}) {
  const style = Style();
  return (
    <Dialog fullScreen open={true} onClose={handleClose}>
      <form className={style.root} autoComplete="off">
        <AppBar className={style.appBar}>
          <Toolbar>
            <IconButton
              edge="start"
              color="inherit"
              onClick={handleClose}
              aria-label="close"
            >
              <CloseIcon />
            </IconButton>
            <Typography variant="h6" className={style.title}>
              Edicion de predicados
            </Typography>
            <img src={Soploon} alt="Soploon" className={style.image} />
            <Button
              style={{ marginLeft: "auto", float: "right" }}
              onClick={guardar}
              autoFocus
              color="inherit"
            >
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
  );
}
