import React from 'react'
import MaterialTable from 'material-table'
import Select from '@material-ui/core/Select'
import MenuItem from '@material-ui/core/MenuItem'
import Axios from 'axios'

let nameClasses = (error) => {
  let out = ''
  error.codeLocation.forEach((item, i) => {
    if (out.length > 0) {
      out += ', '
    }
    let aux = item.path.substring(item.path.lastIndexOf('/') + 1, item.path.length).substring()
    out += aux.substring(0, aux.indexOf('.'))
  })
  return out
}

export default function TableErrors ({ errors, setErrorsSelected }) {
  const handleReviewedChange = (e, error) => {
    // Actualizo la tabla
    errors[errors.indexOf(error)].reviewed = e.target.value
    let aux = [...errors]
    setErrorsSelected(aux)

    const path = {
      id: error.id,
      userId: error.userId,
      projectId: error.projectId,
      date: error.date
    }

    const modifiedError = {
      id: error.id,
      userId: error.userId,
      projectId: error.projectId,
      date: error.date,
      ruleId: error.ruleId,
      versionRule: error.versionRule,
      codeLocation: error.codeLocation,
      representationLocation: error.representationLocation,
      reviewed: e.target.value
    }

    let url = 'http://localhost:8080/soploon/api/users/' + path.userId + '/projects/' + path.projectId + '/corrections/' + path.date + '/errors/' + path.id
    Axios.put(url, modifiedError)
      .then(response => {
      })
      .catch(response => { console.log(response) })
  }

  return (
    <MaterialTable
      localization={{
        pagination: {
          labelRowsPerPage: 'Filas por página',
          labelRowsSelect: 'Filas',
          firstAriaLabel: 'Primera página',
          firstTooltip: 'Primera página',
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
          onClick: () => console.log('ver error')
        }
      ]}
      title='Errores'
      columns={[
                 { title: 'Usuario', field: 'nameUser' },
                 { title: 'Regla', field: 'nameRule' },
                 { title: 'Proyecto', field: 'nameProject' },
                 { title: 'Clase', field: 'nameclass', render: error => nameClasses(error)},
        { title: 'Estado', field: 'reviewed', render: error =>
          <Select labelId='label' name='reviewed' id='select' onChange={(event) => handleReviewedChange(event, error)} value={error.reviewed}>
            <MenuItem key='0' value={0}>No revisado</MenuItem>
            <MenuItem key='1' value={1}>Falso positivo</MenuItem>
            <MenuItem key='2' value={2}>Confirmado</MenuItem>
          </Select>
        }
      ]}
      data={errors} />)
}
