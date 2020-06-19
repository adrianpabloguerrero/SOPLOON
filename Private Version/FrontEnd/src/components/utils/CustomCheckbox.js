import React from 'react';
import Checkbox from '@material-ui/core/Checkbox';
import {withStyles} from '@material-ui/core/styles';
import { orange } from '@material-ui/core/colors';

  const CustomCheckbox = withStyles({
    root: {
     color: orange[400],
     '&$checked': {
       color: orange[600],
     },
    },
    checked: {},
  })(props => <Checkbox color="default" {...props} />);

export {CustomCheckbox};
