import React, { useEffect } from 'react';
import TableRow from '@material-ui/core/TableRow';
import TableCell from '@material-ui/core/TableCell';
import {CustomCheckbox} from '../utils/CustomCheckbox';

const Checkbox = ({ index, item, handleClick }) => {
  return <CustomCheckbox checked={item.selected} value={index} onClick={(event) => handleClick(event,item)} />;
}

export default function TableFilterRow ({ listItems, handleClick }) {
  return (listItems.length > 0) && (
      listItems.map((item,index) => (
         <TableRow key={index}>
           <TableCell component="th" scope="row">
              {item.name}
           </TableCell>
           <TableCell align="center">
             <Checkbox {...{ index, item, handleClick }} />
           </TableCell>
         </TableRow>
     ))
  );
}
