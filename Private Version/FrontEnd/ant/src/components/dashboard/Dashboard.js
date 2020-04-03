import React, { useEffect } from "react";
import Style from "../utils/style.js";
import SmallPaper from "./SmallPaper";
import GroupIcon from "@material-ui/icons/Group";
import ErrorIcon from "@material-ui/icons/Error";
import DoneAllIcon from "@material-ui/icons/DoneAll";
import FolderIcon from "@material-ui/icons/Folder";
import AcumCorrections from "./AcumCorrections";
import TRE from "./TRE";
import ErrorsRate from "./ErrorsRate";
import TopErrors from "./TopErrors";
import LastUse from "./LastUse";
import * as moment from "moment";
import Axios from "axios";

const dateToDefault = () => {
  return moment(new Date()).utcOffset("GMT-03:00").unix() * 1000;
};
const dateFromDefault = () => {
  return moment(dateToDefault()).subtract(2, "years").unix() * 1000;
};

export default function Dashboard() {
  const style = Style();

  const [data, setData] = React.useState({
    usersQuantity: "",
    errorsQuantity: "",
    correctionsQuantity: "",
    projectsQuantity: "",
    errorsRateElement: [],
    errorsTopFive: [],
    acumCorrections: [],
    lastUse: []
  });

  const loadStats = () => {
    const params = {
      date_start: dateFromDefault(),
      date_end: dateToDefault()
    };

    Axios.get("http://localhost:8080/soploon/api/stats", { params })
      .then(response => {
        console.log(response.data);
        setData(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  useEffect(() => {
    loadStats();
  }, []);

  return (
    <div className={style.dashboard}>
      <div className={style.rowDashboard}>
        <SmallPaper
          title="Usuarios"
          number={data.usersQuantity}
          icon={<GroupIcon className={style.iconSmallPaper} />}
        />
        <SmallPaper
          title="Errores"
          number={data.errorsQuantity}
          icon={<ErrorIcon className={style.iconSmallPaper} />}
        />
        <SmallPaper
          title="Correcciones"
          number={data.correctionsQuantity}
          icon={<DoneAllIcon className={style.iconSmallPaper} />}
        />
        <SmallPaper
          title="Proyectos"
          number={data.projectsQuantity}
          icon={<FolderIcon className={style.iconSmallPaper} />}
        />
      </div>
      <div className={style.rowDashboard}>
        <ErrorsRate data={data.errorsRateElement} />
      </div>
      <div className={style.rowDashboard}>
        <AcumCorrections data={data.acumCorrections} />
        <TopErrors data={data.errorsTopFive} />
      </div>
      <div className={style.rowDashboard}>
        <LastUse data={data.lastUse} />
        <AcumCorrections data={data.acumCorrections} />
      </div>
    </div>
  );
}
