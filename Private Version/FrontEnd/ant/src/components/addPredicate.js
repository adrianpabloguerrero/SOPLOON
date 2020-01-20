import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import FormLabel from '@material-ui/core/FormLabel';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import RadioGroup from '@material-ui/core/RadioGroup';
import Radio from '@material-ui/core/Radio';
import Paper from '@material-ui/core/Paper';
import AddPredicateForm from './AddPredicateForm.js';
import SaveIcon from '@material-ui/icons/Save';
import Button from '@material-ui/core/Button';
import DeleteIcon from '@material-ui/icons/Delete';


const useStyles = makeStyles(theme => ({
root: {
     flexGrow: 2,

   },
  gridItem:{
    backgroundColor: "white",
  },
  title:{
    fontSize:18,
    marginBottom:10,
    textAlign:"left",
    fontWeight: "bold",
  },
  button: {
      margin: theme.spacing(1),
    },

}));



export default function SpacingGrid() {
  const [spacing, setSpacing] = React.useState(2);

  const classes = useStyles();

  const handleChange = event => {
    setSpacing(Number(event.target.value));
  };

  return (
    <Grid container className={classes.root} spacing={2}>
    <Grid  item xs={12} className={classes.title}>Agregar predicado </Grid>
    <Grid item xs={12}>
        <Grid container justify="center" spacing={spacing}>
            <Grid className={classes.gridItem} item xs={12} >
               <AddPredicateForm/>
            </Grid>
        </Grid>
        </Grid>

    </Grid>
  );
}
