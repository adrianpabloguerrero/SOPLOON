import React from 'react';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import BottomNavigation from '@material-ui/core/BottomNavigation';
import BottomNavigationAction from '@material-ui/core/BottomNavigationAction';
import BarChartIcon from '@material-ui/icons/BarChart';
import PieChartIcon from '@material-ui/icons/PieChart';
import StorageIcon from '@material-ui/icons/Storage';
import TableErrors from "./TableErrors";
import PieChart from "./PieChart";
import BarChart from "./BarChart";
import Filter from "./Filter";
import Style from '../utils/style';

export default function Errors() {

const [valueNavBar, setValueNavBar] = React.useState(0);
const style = Style();

 //Errores visibles
const [errorsSelected, setErrorsSelected] = React.useState([]);

    return (
      <div className={style.root} >
      <Grid container alignItems="stretch"spacing={3}>
        <Grid item xs={4}>
          <Filter style={style} handleErrorsSelected={setErrorsSelected}/>
        </Grid>
        <Grid item xs={8} className={style.container}>
              <BottomNavigation style = {{marginBottom: "18px"}}
              showLabels
              value={valueNavBar}
              onChange={(event, newValue) => {
              setValueNavBar(newValue);
            }}

              >
              <BottomNavigationAction label="Lista" icon={<StorageIcon />} />
              <BottomNavigationAction label="Pie Chart" icon={<PieChartIcon />} />
              <BottomNavigationAction label="Bar Chart" icon={<BarChartIcon />}  />
              </BottomNavigation>
        { (valueNavBar === 0) && (<TableErrors errors={errorsSelected} setErrorsSelected={setErrorsSelected}/>) }
        { (valueNavBar === 1) && (<Paper className={style.paperInfo}>
          <PieChart errors={errorsSelected} />
        </Paper>) }
        { (valueNavBar === 2) && (<Paper className={style.paperInfo}>
          <BarChart errors={errorsSelected}/>
          </Paper>) }
        </Grid>
      </Grid>
    </div>
    );
}
