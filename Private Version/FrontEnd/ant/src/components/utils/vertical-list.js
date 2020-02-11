import React from 'react';
import List from '@material-ui/core/List';
import ListItem  from '@material-ui/core/ListItem';
import ListItemText  from '@material-ui/core/ListItemText';
import ListSubheader from '@material-ui/core/ListSubheader';
import CircularProgress from '@material-ui/core/CircularProgress';

class VerticalList extends React.Component {
	
	render() {
		var insideElement = this.props.loading ? 
			(<div style={{'marginTop': '25px', 'marginBottom':'25px'}}> <CircularProgress style={{margin: 'auto'}} /> </div>)
			: 
			this.props.items.map( 
				(item,index) => 
				(<ListItem key={index} onClick={item.action} button> <ListItemText primary={item.text}/> </ListItem>)
			)	
			
		return (
			<List subheader={<ListSubheader component="div"> {this.props.title} </ListSubheader>}>
				{ insideElement }
			</List>
		)
	}
}

export default VerticalList;

