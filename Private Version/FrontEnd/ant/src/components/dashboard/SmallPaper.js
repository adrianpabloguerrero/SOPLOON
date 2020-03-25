import React from "react";
import Paper from "@material-ui/core/Paper";
import Style from "../utils/style.js";

export default function SmallPaper({ title, number, icon }) {
  const style = Style();
  return (
    <Paper className={style.smallPaper}>
      <div className={style.ColumnTextSmallPaper}>
        <div className={style.titlePaper}>{title}</div>
        <div className={style.numberPaper}>{number}</div>
      </div>
      <div className={style.ColumnIconSmallPaper}>
        <div>{icon}</div>
      </div>
    </Paper>
  );
}
