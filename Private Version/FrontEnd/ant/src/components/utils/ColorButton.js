import Button from '@material-ui/core/Button';
import {withStyles} from '@material-ui/core/styles';
import { orange } from '@material-ui/core/colors';

const ColorButton = withStyles((theme: Theme) => ({
  root: {
    color: 'white',
    backgroundColor: orange[400],
    '&:hover': {
      backgroundColor: orange[600],
    },
  },
}))(Button);

export {ColorButton};
