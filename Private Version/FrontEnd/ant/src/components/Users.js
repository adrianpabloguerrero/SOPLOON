import React, { useEffect, useState } from 'react';
import axios from 'axios';
import MaterialTable from 'material-table';
import moment from 'moment';
import EditIcon from '@material-ui/icons/Edit';


export default function MaterialTableDemo() {

  const [entries, setEntries] = React.useState({
    data: [
      { id: '',
        name: '',
        role: '',
        creationDate: ''}
    ],
  });

   const [state] = React.useState({
     columns: [
    { title: 'Nombre', field: 'name' },
    { title: 'Rol', field: 'role' },
    { title: 'Fecha de creación', field: 'creationDate'},
    ],
   });

  useEffect(() => {
         axios
         .get('http://localhost:8080/soploon/api/users')
         .then(response => {
         var dateFormat = require('dateformat');

         let data = [];
         response.data.forEach(el => {
         data.push({
         id: el.id,
         name: el.name,
         role: el.role,
         creationDate: dateFormat(new Date (el.creationDate),"dd/mm/yyyy HH:MM:ss"),
     });
 });
     setEntries({ data: data });
 })
 .catch(function(error) {
         console.log(error);
     });
 }, []);


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
               emptyDataSourceMessage: 'No hay usuarios para mostrar',
               deleteTooltip:'Eliminar',
               editTooltip:'Editar',
               filterRow: {
                   filterTooltip: 'Filtrar'
               },
               editRow: {
                 saveTooltip: 'Guardar',
                 cancelTooltip: 'Cancelar',
                 deleteText: '¿Está seguro que desea eliminar el usuario?'
               }
           }
       }}

      title="Gestion de usuarios"
      columns={state.columns}
      data={entries.data}
      editable={{
        onRowUpdate: (newData, oldData) =>
          new Promise(resolve => {
            setTimeout(() => {
              var dateFormat = require('dateformat');
              resolve();
              const data = [...entries.data];
              const position = data.indexOf(oldData);
              data[position] = newData;
              let newProject = {
                id: newData.id,
                name: newData.name,
                role: newData.role,
                creationDate: new Date("2015-03-25T12:00:00Z")
              }
              console.log(newProject);
              axios
               .put(`http://localhost:8080/soploon/api/users/${oldData.id}`,newProject)
           setEntries({ ...entries, data });
       }, 600);
          }),
      }}
    />

  );
}
