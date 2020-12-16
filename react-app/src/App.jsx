import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import TeamList from './TeamList';

class App extends Component {
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <TeamList />
        </header>
      </div>
    );
  }
}

export default App;

