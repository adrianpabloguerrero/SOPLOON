import React from "react";
import Paper from "@material-ui/core/Paper";
import Style from "../utils/style.js";
import Highcharts from "highcharts";
import Exporting from "highcharts/modules/exporting";
import HighchartsReact from "highcharts-react-official";
Exporting(Highcharts);

export default function ErrorsRate({ data }) {
  const style = Style();
  return (
    <Paper className={style.errorsRate}>
      <HighchartsReact
        highcharts={Highcharts}
        options={{
          chart: {
            type: "pie"
          },
		  title: {
            text: "Tipos de errores"
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
