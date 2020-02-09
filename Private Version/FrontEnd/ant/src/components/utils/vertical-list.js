import React from 'react';
import List from '@material-ui/core/List';
import ListItem  from '@material-ui/core/ListItem';
import ListItemText  from '@material-ui/core/ListItemText';
import ListSubheader from '@material-ui/core/ListSubheader';

class VerticalList extends React.Component {
	
	render() {
		return (
			<List subheader={<ListSubheader component="div"> {this.props.title} </ListSubheader>}>
				{ this.props.items.map( 
					(item,index) => 
					(<ListItem key={index} onClick={item.action} button> <ListItemText primary={item.text}/> </ListItem>)
				)}	
			</List>
		)
	}
}

export default VerticalList;

