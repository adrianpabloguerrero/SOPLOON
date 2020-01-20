import React, { useEffect, useState } from 'react';
import axios from 'axios';
import MaterialTable from 'material-table';
import moment from 'moment';

export default function MaterialTableDemo() {

  const [entries, setEntries] = React.useState({
    data: [
      { id: '',
        version: '',
        name: '',
        description: '',
        code:'',
        activated:''
      }
    ],
  });

   const [state] = React.useState({

     columns: [
    { title: 'Nombre', field: 'name' },
    { title: 'Description', field: 'description' },
    { title: 'Version', field: 'version' },
    ],
   });

  useEffect(() => {
         axios
         .get('http://localhost:8080/soploon/api/predicates/')
         .then(response => {

         let data = [];
         response.data.forEach(el => {
         data.push({
         id: el.id,
         version: el.version,
         name: el.name,
         description: el.description,
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

      title="Predicados activos"
      columns={state.columns}
      data={entries.data}

      editable={{
        onRowAdd: {} ,
        onRowUpdate: (newData, oldData) =>
          new Promise(resolve => {
            setTimeout(() => {
              resolve();
              if (oldData) {
                setEntries(prevState => {
                  const data = [...prevState.data];
                  data[data.indexOf(oldData)] = newData;
                  return { ...prevState, data };
                });
              }
            }, 600);
          }),

      }}


    />
  );
}
