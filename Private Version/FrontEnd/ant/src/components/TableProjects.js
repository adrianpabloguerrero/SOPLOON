import React, { useEffect, useState } from 'react';
import axios from 'axios';
import MaterialTable from 'material-table';
import moment from 'moment';

export default function MaterialTableDemo() {

  const [entries, setEntries] = React.useState({
    data: [
      { id: '',
        userId: '',
        name: ''
      }
    ],
  });

   const [state] = React.useState({
     columns: [
    { title: 'Usuario', field: 'userId' },
    { title: 'Nombre', field: 'name' },
    ],
   });

  useEffect(() => {
         axios
         .get('http://localhost:8080/soploon/api/projects')
         .then(response => {

         let data = [];
         response.data.forEach(el => {
         data.push({
         id: el.id,
         userId: el.userId,
         name: el.name
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
               emptyDataSourceMessage: 'No hay proyectos para mostrar',
               deleteTooltip:'Eliminar',
               editTooltip:'Editar',
               filterRow: {
                   filterTooltip: 'Filtrar'
               },
               editRow: {
                 saveTooltip: 'Guardar',
                 cancelTooltip: 'Cancelar',
                 deleteText: '¿Está seguro que desea eliminar el proyecto?'
               }
           }
       }}

      title="Gestion de proyectos"
      columns={state.columns}
      data={entries.data}
      editable={{

        onRowUpdate: (newData, oldData) =>
          new Promise(resolve => {
            setTimeout(() => {
              resolve();
              const data = [...entries.data];
              const position = data.indexOf(oldData);
              data[position] = newData;
            
              let newProject = {
                id: newData.id,
                idUser: newData.idUser,
                name: newData.name
              }

              axios
               .put(`http://localhost:8080/soploon/api/projects/${oldData.id}`,newProject)
           setEntries({ ...entries, data });
       }, 600);
          }),
      }}
    />
  );
}
