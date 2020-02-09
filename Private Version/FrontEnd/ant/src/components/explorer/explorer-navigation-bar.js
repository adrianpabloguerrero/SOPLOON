import React from 'react';
import NavigationBar from '../utils/navigation-bar';

class ExplorerNavigationBar extends React.Component {
		
	constructor(props) {
		super(props);
		this.state = { items: []};
	}
	
	static getDerivedStateFromProps(props, state) {
		const {user, project, correction} = props.data;
		const {selectInit, selectUser, selectProject} = props.actions;

		var newState = { items: [{'text': 'Inicio', 'action': selectInit }] };
		
		if (user != null) {
			newState.items.push({'text': user.name, 'action': selectUser});
			if (project != null) {
				newState.items.push({'text': project.name, 'action': selectProject});
				if (correction != null) {
					newState.items.push({'text': new Date(correction.date).toLocaleString()});
				}
			}
		}
		
		return newState;
	}
	
	
	render() {
		return (
			<NavigationBar items={this.state.items}/>
		)
	}
}

export default ExplorerNavigationBar;

