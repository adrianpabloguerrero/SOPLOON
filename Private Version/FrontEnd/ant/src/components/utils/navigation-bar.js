import React from 'react';
import Breadcrumbs from '@material-ui/core/Breadcrumbs';
import Link from '@material-ui/core/Link';
import NavigateNextIcon from '@material-ui/icons/NavigateNext';

class NavigationBar extends React.Component {
	
	render() {
		return (
			<Breadcrumbs separator={<NavigateNextIcon fontSize="small" />} aria-label="breadcrumb">
				{ this.props.items.map( (aux,index) => (<Link key={index} color="inherit" onClick={aux.action}>{aux.text}</Link>) ) }
			</Breadcrumbs>
		)
	}
}

export default NavigationBar;

