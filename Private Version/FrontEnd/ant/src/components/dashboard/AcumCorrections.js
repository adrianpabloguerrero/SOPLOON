import React from "react";
import Paper from "@material-ui/core/Paper";
import Style from "../utils/style.js";
import Highcharts from "highcharts";
import HighchartsReact from "highcharts-react-official";
import ChartOptions from "../utils/ChartOptions.js";
import exporting from "highcharts/modules/exporting";
import * as moment from "moment";
exporting(Highcharts);

ChartOptions(Highcharts);

const processData = data => {
  let out = [];
  data.map(element => {
    let newElement = [];
    newElement[0] = Date.parse(element.name);
    newElement[1] = element.y;
    out.push(newElement);
  });
  return out;
};

export default function AcumCorrections({ data }) {
  const style = Style();
  const dataChart = processData(data);
  return (
    <Paper className={style.errorsRate}>
      <HighchartsReact
        highcharts={Highcharts}
        options={{
          chart: {
            type: "line"
          },
          title: {
            text: "Uso de la herramienta"
          },
          xAxis: {
            type: "datetime"
          },
          yAxis: {
            title: {
              text: "Cantidad"
            }
          },
          plotOptions: {
            line: {
              dataLabels: {
                enabled: true
              },
              enableMouseTracking: true
            }
          },
          exporting: {
            enabled: false
          },
          tooltip: {},
          series: [
            {
              name: "Correcciones",
              data: dataChart
            }
          ]
        }}
      />
    </Paper>
  );
}
