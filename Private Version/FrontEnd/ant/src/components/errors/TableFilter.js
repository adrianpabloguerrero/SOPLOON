import React from 'react';
import TableFilterRow from './TableFilterRow';
import { List, AutoSizer } from "react-virtualized";


export default function TableFilter ({ listItems, handleClick }) {

  const getRowRenderer = ({  index, isScrolling, key, style  }) => {
     return <TableFilterRow key= {key} style={style} item={listItems[index]} handleClick={handleClick}/>;
   }

  return (
    <div style={{display:"flex"}}>
    <AutoSizer>
    {
      ({width,height}) => {
        return   <List
          className={"List"}
          width={width}
          height={350}
          rowHeight={50}
          rowRenderer={getRowRenderer}
          rowCount={listItems.length}
           />
      }
    }
    </AutoSizer>
    </div>


  )
}
