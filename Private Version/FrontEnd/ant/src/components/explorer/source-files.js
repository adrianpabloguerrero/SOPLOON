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
import Button from '@material-ui/core/Button';
import Snackbar from '@material-ui/core/Snackbar';
import Alert from '@material-ui/lab/Alert';
import FileSaver from 'file-saver';

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
		this.state = { 'value': 0, 'copyInfo': { 'open' : false, 'fileName': null } };
		this.testRef = React.createRef();
	}
	
	copyToClipboard = (textToCopy) => {
		const el = document.createElement('textarea'); 
		el.value = textToCopy;   
		el.setAttribute('readonly', '');
		el.style.position = 'absolute';                 
		el.style.left = '-9999px';
		document.body.appendChild(el);
		const selected = document.getSelection().rangeCount > 0 ? document.getSelection().getRangeAt(0) : false;
		el.select();
		document.execCommand('copy');
		document.body.removeChild(el);
		if (selected) { 
			document.getSelection().removeAllRanges();
			document.getSelection().addRange(selected);
		}
	};
	
	openCopyInfo = (sourceFile) => {
		this.setState({ 'value': this.state.value, 'copyInfo': { 'open': true, 'fileName': sourceFile.name } });
	}
	
	closeCopyInfo = () => {
		this.setState({ 'value': this.state.value, 'copyInfo': { 'open': false, 'fileName': this.state.copyInfo.fileName } });
	}
	
	handleCopy = (sourceFile) => {
		this.copyToClipboard(sourceFile.source);
		this.openCopyInfo(sourceFile);
	}
	
	handleDownload = (sourceFile) => {
		var blob = new Blob([sourceFile.source], {type: "text/plain;charset=utf-8"});
		FileSaver.saveAs(blob, sourceFile.name);
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
						<Grid item xs={12}>
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
										<div className={this.props.style.smallText} style={{'textTransform':'none'}}>
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
					</Grid>
					{
						this.props.sourceFiles.map( 
							(sourceFile, index) =>
							(
								<TabPanel key={sourceFile.id} value={this.state.value} index={index} style={{overflow: 'auto'}}>
									<div style={{'position':'relative'}}>
										<div style={{'position': 'absolute', 'top':'5px', 'right':'5px'}}>
											<Tooltip title="Descargar" aria-label="download">
												<Button onClick={ () => this.handleDownload(sourceFile) } color='inherit' component="span" >
													<DownloadIcon className={this.props.style.mediumText} />
												</Button>
											</Tooltip>
											<Tooltip title="Copiar" aria-label="copy">
												<Button onClick={ () => this.handleCopy(sourceFile) } color='inherit' component="span" >
													<FileCopyIcon className={this.props.style.mediumText} />
												</Button>
											</Tooltip>
										</div>
										<SyntaxHighlighter className={this.props.style.sourceCode} language={this.props.language} style={docco}>
											{ sourceFile.source.replace(/\t/g,"   ") }
										</SyntaxHighlighter>								
									</div>
								</TabPanel>
							)
					)}
					<Snackbar open={this.state.copyInfo.open} autoHideDuration={1500} onClose={this.closeCopyInfo}>
						<Alert elevation={6} variant="filled" severity="info">
							{ this.state.copyInfo.fileName } copiado al portapapeles!
						</Alert>
					</Snackbar>
				</div>
			)
		} else {
			return (<div> No hay archivos abiertos </div>);
		}		
	}
	
}

export default SourceFiles;

