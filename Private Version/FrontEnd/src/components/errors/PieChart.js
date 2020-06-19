import React from 'react';
import Highcharts from 'highcharts';
import PieChart from 'highcharts-react-official';
import exporting from "highcharts/modules/exporting";
exporting(Highcharts);

const proccesData = errors => {
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
   result.push({name:i,y:(100*item/(errors.length))})
  });
  return result;
}

export default function PieChartErrors ({ errors }) {
  return (
    <PieChart
    highcharts={Highcharts}
    options={{
      title: {
        text: 'Porcentaje de errores'
      },
      chart: {
      type: "pie"
    },
    exporting: {
             enabled: true
           },
    tooltip: {
        pointFormat: "Cantidad: {point.y:.2f} %"
    },
      series: [{
        name:"Cantidad",
        data: proccesData(errors)
      }]
    }}
/>
  );
}
