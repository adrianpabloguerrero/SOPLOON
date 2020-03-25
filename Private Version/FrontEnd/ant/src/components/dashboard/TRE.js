import React from "react";
import Paper from "@material-ui/core/Paper";
import Style from "../utils/style.js";
import Highcharts from "highcharts";
import HighchartsReact from "highcharts-react-official";
import exporting from "highcharts/modules/exporting";
exporting(Highcharts);

export default function TRE() {
  const style = Style();
  return (
    <Paper className={style.chartTRE}>
      <HighchartsReact
        highcharts={Highcharts}
        options={{
          title: {
            text: "Tasa de reducción de errores"
          },
          chart: {
            type: "column"
          },
          xAxis: {
            type: "category",
            labels: {
              rotation: -45,
              style: {
                fontSize: "7px",
                fontFamily: "Verdana, sans-serif"
              }
            }
          },
          yAxis: {
            min: 0,
            title: {
              text: "Cantidad total de errores"
            }
          },
          legend: {
            enabled: false
          },
          exporting: {
            enabled: false
          },
          series: [
            {
              name: "Cantidad total",
              data: []
            }
          ]
        }}
      />
    </Paper>
  );
}
