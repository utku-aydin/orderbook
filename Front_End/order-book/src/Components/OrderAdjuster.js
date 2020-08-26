import React from "react";
import { Table, Button, Col, Row, Container } from "react-bootstrap";
import { ArrowUpShort, ArrowDownShort } from 'react-bootstrap-icons'
import styled from 'styled-components';

const GreenPointer = styled.p`
	color: #000;
	:hover {
		color: 	#008000;
		cursor: pointer;
    }
    height: 1px
    
`
const RedPointer = styled.p`
	color: #000;
	:hover {
		color: #ed1212;
		cursor: pointer;
    }
    height: 1px
    
`
const RedText = styled.p`
    color: #ed1212;
`

const GreenText = styled.p`
    color: #008000;
`
class OrderAdjuster extends React.Component {

    //     state = {
    //         value: ""
    //     };

    //     constructor(props) {
    //         super(props);
    //         this.state.value = this.props.value;
    //         this.addOne = this.addOne.bind(this);
    //         this.minusOne = this.minusOne.bind(this);
    //     }

    //  addOne() {
    //     this.setState({ value: this.state.value + 1 });

    // }

    // minusOne(){
    //     this.setState({ value: this.state.value - 1 });
    // }


    render() {
        console.log("VAlue in order adjuster is " + this.props.value)
        let roundedNumber = (Math.round(this.props.editValue * 100) / 100).toFixed(2);
        return (
            <React.Fragment>
                <Container fluid>
                    <Row>
                        {this.props.value}
                        <GreenPointer onClick={(evt) => this.props.handleChangeNumber(this.props.name, this.props.tickSize, evt)}><ArrowUpShort /></GreenPointer>
                        <RedPointer onClick={(evt) => this.props.handleChangeNumber(this.props.name, -this.props.tickSize, evt)}><ArrowDownShort /></RedPointer>
                        {this.props.editValue >= this.props.value ? null : <RedText>{roundedNumber}</RedText>}
                        {this.props.editValue <= this.props.value ? null : <GreenText>{roundedNumber}</GreenText>}
                    </Row>
                </Container>
            </React.Fragment>
        )
    }


}

export default OrderAdjuster;
