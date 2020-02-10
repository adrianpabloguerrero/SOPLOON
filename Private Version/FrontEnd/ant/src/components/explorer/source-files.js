import React from 'react';
import SyntaxHighlighter from 'react-syntax-highlighter';
import { docco } from 'react-syntax-highlighter/dist/esm/styles/hljs';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import Close from '@material-ui/icons/Close';
import Grid from '@material-ui/core/Grid';
import FileCopyIcon from '@material-ui/icons/FileCopy';
import DownloadIcon from '@material-ui/icons/GetApp';
import Tooltip from '@material-ui/core/Tooltip';

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <Typography
      component="div"
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && <Box>{children}</Box>}
    </Typography>
  );
}

class SourceFiles extends React.Component {
			
	constructor(props) {
		super(props);
		this.state = { 'value': 0 };
	}
	
	handleChange = (event, newValue) => {
		this.setState({'value': newValue});
	}
  
	handleClose = (event, index, sourceFile) => {
		if (index === this.props.sourceFiles.length - 1 && this.state.value === index && index > 0)
			this.setState({'value': index-1});
		this.props.closeAction(sourceFile);
		event.stopPropagation();
	}
	
	render() {	
		if (this.props.sourceFiles.length > 0) {
			return (
				<div>
					<Grid container>
						<Grid item xs={8} sm={10}>
							<Tabs 
							  indicatorColor="primary"
							  textColor="primary"
							  variant="scrollable"
							  scrollButtons="auto"
							  value={this.state.value} onChange={this.handleChange}>
							  {
								this.props.sourceFiles.map( 
									(sourceFile, index) => 
									( <Tab  key={sourceFile.id} label = {
										<div className={this.props.style.smallText}>
											{sourceFile.name}
											<IconButton className={this.props.style.iconButton} component="span" onClick={(event) => this.handleClose(event,index,sourceFile)}>
												<Close className={this.props.style.smallText} />
											</IconButton>
										</div> } />
									)
								)
							  }							  
							</Tabs>
						</Grid>
						<Grid item xs={4} sm={2} style={{textAlign: 'right'}}>
							<div>
								<Tooltip title="Descargar" aria-label="download">
									<IconButton className={this.props.style.iconButton}  component="span" >
										<DownloadIcon className={this.props.style.mediumText} />
									</IconButton>
								</Tooltip>
								<Tooltip title="Copiar" aria-label="copy">
									<IconButton className={this.props.style.iconButton}  component="span" >
										<FileCopyIcon className={this.props.style.mediumText} />
									</IconButton>
								</Tooltip>
							</div>
						</Grid>
					</Grid>
					{
						this.props.sourceFiles.map( 
							(sourceFile, index) =>
							(
								<TabPanel key={sourceFile.id} value={this.state.value} index={index} style={{overflow: 'auto'}}>
									<div >
										<SyntaxHighlighter className={this.props.style.sourceCode} language={this.props.language} style={docco}>
											{ sourceFile.source.replace(/\t/g,"   ") }
										</SyntaxHighlighter>								
									</div>
								</TabPanel>
							)
					)}

				</div>
			)
		} else {
			return (<div> No hay archivos abiertos </div>);
		}		
	}
	
}

export default SourceFiles;

