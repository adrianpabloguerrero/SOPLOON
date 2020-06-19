import React from 'react';
import {CustomCheckbox} from '../utils/CustomCheckbox';

const Checkbox = ({ item, handleClick }) => {
  return <CustomCheckbox checked={item.selected} onClick={(event) => handleClick(event,item)} />;
}

export default function TableFilterRow ({ item, handleClick, style}) {
  return(
        <div style={style}>
              <div style={{"display":"flex", "alignItems":"center","justifyContent":"space-between"}}>
              {item.name}
               <Checkbox {...{ item, handleClick }} />
              </div>
        </div>
       )
}
