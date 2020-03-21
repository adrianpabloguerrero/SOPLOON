import React from 'react';
import Highcharts from 'highcharts';
import HighchartsReact from 'highcharts-react-official';
import exporting from "highcharts/modules/exporting";
exporting(Highcharts);


const processData = errors => {
  const result = [];
  const map = new Map();
  for (const item of errors){
    if (!map.has(item.nameRule)){
      map.set(item.nameRule,1);
    }
    else {
      map.set(item.nameRule,map.get(item.nameRule)+1);
    }
  }
  map.forEach((item, i) => {
   result.push({name:i,y:item})
  });
  return result;
}

export default function BarChartErrors ({ errors }) {
  return (
      <HighchartsReact
        highcharts={Highcharts}
        options={{
          title: {
            text: 'Cantidad de errores'
          },
          chart: {
            type: 'column'
          },
          xAxis: {
              type: 'category',
              labels: {
                  rotation: -45,
                  style: {
                      fontSize: '7px',
                      fontFamily: 'Verdana, sans-serif'
                  }
              }
          },
          yAxis: {
                    min: 0,
                    title: {
                        text: 'Cantidad total de errores'
                    }
                },
          legend: {
            enabled: false
          },
          exporting: {
                 enabled: true
          },
          series: [{
            name:"Cantidad total",
            data: processData(errors)
          }]
        }}
      />
)}
