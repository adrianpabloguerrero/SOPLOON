import React from "react";
import Paper from "@material-ui/core/Paper";
import Style from "../utils/style.js";
import Highcharts from "highcharts";
import PieChart from "highcharts-react-official";
import exporting from "highcharts/modules/exporting";
exporting(Highcharts);

export default function ErrorsRate({ data }) {
  const style = Style();
  return (
    <Paper className={style.errorsRate}>
      <PieChart
        highcharts={Highcharts}
        options={{
          title: {
            text: "Tipos de errores"
          },
          chart: {
            type: "pie"
          },
          exporting: {
            enabled: false
          },
          tooltip: {
            pointFormat: "Cantidad: {point.y:.2f} %"
          },
          series: [
            {
              name: "Cantidad",
              data: data
            }
          ]
        }}
      />
    </Paper>
  );
}
