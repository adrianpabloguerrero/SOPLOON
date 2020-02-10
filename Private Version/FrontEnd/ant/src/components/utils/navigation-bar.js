import React from 'react';
import Breadcrumbs from '@material-ui/core/Breadcrumbs';
import Link from '@material-ui/core/Link';
import NavigateNextIcon from '@material-ui/icons/NavigateNext';
import Button from '@material-ui/core/Button';

class NavigationBar extends React.Component {
	
	render() {
		return (
			<Breadcrumbs separator={<NavigateNextIcon fontSize="small" />} aria-label="breadcrumb">
				{ this.props.items.map( 
					(item,index) => 
					(<Button style={{textTransform: 'none'}} color='inherit' key={index} onClick={item.action}>{item.text}</Button>) 
				)}
			</Breadcrumbs>
		)
	}
}

export default NavigationBar;

