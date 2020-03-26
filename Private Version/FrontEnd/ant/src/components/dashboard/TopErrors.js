import React from "react";
import Paper from "@material-ui/core/Paper";
import Style from "../utils/style.js";

export default function TopErrors({ data }) {
  const style = Style();

  return (
    <Paper className={style.topErrors}>
      <div className={style.titlePaper}> Errores más frecuentes</div>
      <div>
        <div className={style.headerTopErrors}>
          <div> Nombre </div>
          <div> Ocurrencias </div>
        </div>
        {data.map((dato, index) => (
          <div className={style.rowErrors}>
            <div>
              {index + 1} - {dato.name}
            </div>
            <div>{dato.y}</div>
          </div>
        ))}
      </div>
    </Paper>
  );
}
