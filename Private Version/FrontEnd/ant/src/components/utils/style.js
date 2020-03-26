import { makeStyles } from "@material-ui/core/styles";

const style = makeStyles(theme => ({
  root: {
    flexGrow: 1
  },
  paper: {
    padding: theme.spacing(1.5),
    textAlign: "center",
    color: theme.palette.text.secondary
  },
  label: {
    fontWeight: "inherit",
    color: "inherit"
  },
  labelRoot: {
    display: "flex",
    alignItems: "center",
    padding: theme.spacing(0.5, 0)
  },
  labelIcon: {
    marginRight: theme.spacing(1),
    fontSize: "small"
  },
  labelText: {
    fontWeight: "inherit",
    flexGrow: 1
  },
  mediumText: {
    fontSize: "1.2rem"
  },
  smallText: {
    fontSize: "small"
  },
  iconButton: {
    padding: "8px",
    margin: "4px"
  },
  sourceCode: {
    fontSize: "small",
    textAlign: "left"
  },

  //errors
  paperInfo: {
    height: 500,
    display: "flex",
    alignItems: "center",
    justifyContent: "center"
  },
  container: {
    display: "flex",
    flexDirection: "column",
    maxHeight: "100%"
  },
  itemGrafico: {
    maxHeight: "40%"
  },
  rootError: {
    flexGrow: 1,
    minHeight: "120%",
    maxHeight: "120%",
    width: "100%"
  },
  paperError: {
    padding: "0px 16px 16px 16px",
    color: theme.palette.text.secondary,
    width: "100%",
    minHeight: 575,
    maxHeight: 575,
    display: "flex",
    flexDirection: "column"
  },
  form: {
    display: "flex",
    flexDirection: "row",
    justifyContent: "space-between"
  },
  select: {
    width: "100%"
  },
  itemFilter: {
    margin: "10px 8px 10px 0px"
  },
  itemFilterDate: {
    display: "flex",
    justifyContent: "space-between"
  },

  itemFilterTable: {
    margin: "30px 8px 10px 0px"
  },
  tableFilter: {
    margin: "30px 8px 10px 0px"
  },
  //Rules
  appBar: {
    position: "relative",
    backgroundColor: "#222"
  },
  image: {
    width: "30px",
    marginLeft: "10px"
  },
  rootRule: {
    display: "flex",
    flexWrap: "wrap"
  },
  textField: {
    marginLeft: theme.spacing(5),
    marginRight: theme.spacing(5)
  },
  button: {
    margin: theme.spacing(1)
  },
  title: {
    marginLeft: theme.spacing(2),
    color: "#9d9d9d"
  },
  //Dashboard
  dashboard: {
    display: "flex",
    justifyContent: "space-between",
    flexDirection: "column",
    flexWrap: "wrap"
  },
  rowDashboard: {
    display: "flex",
    width: "100%"
  },
  smallPaper: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    display: "flex",
    flex: 1,
    flexDirection: "row",
    flexWrap: "wrap",
    alignItems: "flex-start"
  },
  chartTRE: {
    marginTop: theme.spacing(3),
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    width: "40em"
  },
  errorsRate: {
    marginTop: theme.spacing(3),
    marginRight: theme.spacing(1),
    marginLeft: theme.spacing(1),
    width: "100%"
  },
  ColumnTextSmallPaper: {
    width: "60%" // is 50% of container width
  },
  ColumnIconSmallPaper: {
    width: "40%",
    alignItems: "center",
    justifyContent: "center"
  },
  titlePaper: {
    fontWeight: "bold",
    fontSize: "20px"
  },
  numberPaper: {
    fontWeight: "bold",
    fontSize: "50px"
  },
  iconSmallPaper: {
    color: "#fdb039",
    fontSize: 90
  },
  topErrors: {
    marginTop: theme.spacing(3),
    marginRight: theme.spacing(1),
    marginLeft: theme.spacing(1),
    width: "30%"
  },
  rowErrors: {
    display: "flex",
    justifyContent: "space-between",
    marginTop: theme.spacing(2),
    marginRight: theme.spacing(1),
    marginLeft: theme.spacing(1)
  },
  headerTopErrors: {
    display: "flex",
    justifyContent: "space-between",
    marginTop: theme.spacing(2),
    marginTop: theme.spacing(2),
    marginRight: theme.spacing(1),
    marginLeft: theme.spacing(1),
    fontWeight: "bold"
  }
}));

export default style;
