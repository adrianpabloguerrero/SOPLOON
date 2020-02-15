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
  }
}));

export default style;