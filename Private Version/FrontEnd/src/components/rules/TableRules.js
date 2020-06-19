import React from 'react';
import MaterialTable from 'material-table';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import {CustomCheckbox} from '../utils/CustomCheckbox';

function TableRules ({handleClickOpen, handleVersionChange, handleActivatedChange, entries}){

  return (
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
      title="Reglas"
      columns={[
			 { title: 'Nombre', field: 'selected.name' },
			 { title: 'Descripción', field: 'selected.description' },
			 { title: 'Version', field: 'selected.version', render: rule =>
				<Select onChange={(event) => handleVersionChange(event,rule)} labelId="label" id="select" value={rule.selected.version} disabled={rule.versions.length === 1}>
					{rule.versions.map((version,index) =>
					  <MenuItem key={version.version} value={version.version}>{version.version}</MenuItem>
					)}
				</Select>
			 },
			 { title: 'Activada', field: 'selected.activated', render: rule =>
				<CustomCheckbox checked={rule.selected.activated} onChange={(event) => handleActivatedChange(event,rule)} value="activatedRule"/>
			 }
		]}
      data={entries.data}
    />
  )
}

export default React.memo(TableRules);
