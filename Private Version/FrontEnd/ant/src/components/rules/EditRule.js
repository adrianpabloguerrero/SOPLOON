import React from 'react';
import Button from "@material-ui/core/Button";
import Dialog from '@material-ui/core/Dialog';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import IconButton from '@material-ui/core/IconButton';
import Typography from '@material-ui/core/Typography';
import CloseIcon from '@material-ui/icons/Close';
import Soploon from '../utils/images/soploon.png';
import TextField from "@material-ui/core/TextField";
import Style from '../utils/style'

export default function EditRule ({open, handleClose, guardar, handleChange, inputs}) {

  const style = Style();
  return (
    <Dialog fullScreen open={open} onClose={handleClose} >
      <form className={style.root} autoComplete='off'>
        <AppBar className={style.appBar}>
          <Toolbar>
            <IconButton edge='start' color='inherit' onClick={handleClose} aria-label='close'>
              <CloseIcon />
            </IconButton>
            <Typography variant='h6' className={style.title}>
          Edicion de reglas
          </Typography>
            <img src={Soploon} alt='Soploon' className={style.image} / >
            <Button style={{ marginLeft: 'auto', float: 'right' }} onClick={guardar} autoFocus color='inherit'>
          Guardar
        </Button>
          </Toolbar>
        </AppBar>
        <TextField
          id='name'
          label='Nombre'
          name='name'
          value={inputs.name}
          onChange={handleChange}
          style={{ margin: 20 }}
          placeholder='Ej: Atributo público'
          fullWidth
          required
          margin='normal'
          InputLabelProps={{
            shrink: true
          }}
      />
        <TextField
          id='description'
          label='Descripción'
          name='description'
          value={inputs.description}
          onChange={handleChange}
          style={{ margin: 20 }}
          placeholder='Ej: Los atributos públicos pueden ser modificados desde el exterior...'
          fullWidth
          required
          margin='dense'
          InputLabelProps={{
            shrink: true
          }}
      />
        <TextField
          id='link'
          label='Link'
          name='link'
          value={inputs.link}
          onChange={handleChange}
          style={{ margin: 20 }}
          required
          placeholder='Ej: http://www.google.com.ar'
          fullWidth
          margin='dense'
          InputLabelProps={{
            shrink: true
          }}
      />
        <TextField
          id='query'
          label='Query'
          name='query'
          value={inputs.query}
          onChange={handleChange}
          style={{ margin: 20 }}
          required
          placeholder='Ej: public_attribute'
          fullWidth
          margin='dense'
          InputLabelProps={{
            shrink: true
          }}
      />
        <TextField
          id='code'
          label='Código'
          style={{ margin: 20 }}
          name='code'
          value={inputs.code}
          onChange={handleChange}
          placeholder='Ej: 	public_attribute(ID):- field_declaration(ID, _, _, _, _, _, _, compilation_unit(UNIT)), public_field(ID), not(final_field(ID)), not(static_field(ID)), model_unit(UNIT).'
          fullWidth
          required
          margin='dense'
          multiline
          rows={10}
          rowsMax={10}
          InputLabelProps={{
            shrink: true
          }}
      />
      </form>
    </Dialog>
  )
}
