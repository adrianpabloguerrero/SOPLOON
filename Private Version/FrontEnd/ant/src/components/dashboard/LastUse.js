import React from "react";
import Paper from "@material-ui/core/Paper";
import Style from "../utils/style.js";
import * as moment from "moment";

export default function lastUse({ data }) {
  const style = Style();
  console.log(data);
  return (
    <Paper className={style.lastUse}>
      <div className={style.titlePaper}> Ultima corrección</div>
      <div className={style.rowErrors}>
        <div> Usuario: </div>
        <div> {data.nameUser}</div>
      </div>
      <div className={style.rowErrors}>
        <div> Projecto: </div>
        <div> {data.nameProject}</div>
      </div>
      <div className={style.rowErrors}>
        <div> Fecha: </div>
        <div> {new moment(data.date).format("DD-MM-YYYY HH:mm:ss")} </div>
      </div>
      <div className={style.headerTopErrors}>
        <div> Error </div>
        <div> Ocurrencias </div>
      </div>
      {data.errors !== undefined &&
        data.errors.map((dato, index) => (
          <div key={index} className={style.rowErrors}>
            <div>
              {index + 1} - {dato.name}
            </div>
            <div>{dato.y}</div>
          </div>
        ))}
    </Paper>
  );
}
