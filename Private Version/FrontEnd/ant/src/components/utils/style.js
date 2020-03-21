import { makeStyles } from '@material-ui/core/styles';

const style = makeStyles(theme => ({
  root: {
    flexGrow: 1,
  },
  paper: {
    padding: theme.spacing(1.5),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  },
  label: {
    fontWeight: 'inherit',
    color: 'inherit',
  },
  labelRoot: {
    display: 'flex',
    alignItems: 'center',
    padding: theme.spacing(0.5, 0),
  },
  labelIcon: {
    marginRight: theme.spacing(1),
	fontSize: 'small',
  },
  labelText: {
    fontWeight: 'inherit',
    flexGrow: 1,
  },
  mediumText: {
	fontSize: '1.2rem',
  },
  smallText: {
	fontSize: 'small',
  },
  iconButton: {
	padding: '8px',
	margin: '4px',
  },
  sourceCode: {
	fontSize: 'small',
	textAlign: 'left',
  },

  //errors
  paperInfo: {
    height:500,
    display:'flex',
    alignItems:'center',
    justifyContent:'center'
  },
  container: {
    display:'flex',
    flexDirection:'column',
    maxHeight:"100%"
  },
  itemGrafico: {
    maxHeight:"40%",
  },
  rootError: {
   flexGrow: 1,
   minHeight: "120%",
   maxHeight: "120%",
   width: '100%',
 },
 paperError: {
   padding: "0px 16px 16px 16px",
   color: theme.palette.text.secondary,
   width: '100%',
   minHeight:575,
   maxHeight: 575,
   display:"flex",
   flexDirection:"column",
 },
 form: {
   display: 'flex',
   flexDirection:"row",
   justifyContent: 'space-between',
 },
 select: {
   width:'100%',
 },
 itemFilter:{
  margin: "10px 8px 10px 0px"
},
 itemFilterDate:{
  display:'flex',
  justifyContent:'space-between'
},

itemFilterTable:{
  margin: "30px 8px 10px 0px"
},
 tableFilter:{
  margin: "30px 8px 10px 0px",
 }

}));

export default style;
