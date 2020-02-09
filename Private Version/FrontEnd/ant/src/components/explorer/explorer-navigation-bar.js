import React from 'react';
import NavigationBar from '../utils/navigation-bar';

class ExplorerNavigationBar extends React.Component {
		
	shouldComponentUpdate(nextProps, nextState) {
		return (nextProps.data !== this.props.data);
	}
  
	structure = (data, actions) => {
		const {user, project, correction} = data;
		const {selectInit, selectUser, selectProject} = actions;

		var items = [{'text': 'Inicio', 'action': selectInit }];
		
		if (user != null) {
			items.push({'text': user.name, 'action': selectUser});
			if (project != null) {
				items.push({'text': project.name, 'action': selectProject});
				if (correction != null) {
					items.push({'text': new Date(correction.date).toLocaleString()});
				}
			}
		}
		
		return items;
	}
	
	render() {
		var items = this.structure(this.props.data, this.props.actions);
		
		return (
			<NavigationBar items={items}/>
		)
	}
}

export default ExplorerNavigationBar;

