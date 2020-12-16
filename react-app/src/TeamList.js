import React, { Component } from 'react';
class TeamList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      teams: [],
      isLoading: false,
      teamName: ''
    };
    this.addTeam = this.addTeam.bind(this);
    this.onTeamNameChange = this.onTeamNameChange.bind(this);
  }

  async componentDidMount() {
    this.setState({isLoading: true});

    const response = await fetch('http://localhost:8888/teams/');
    const data = await response.json();
    this.setState({teams: data, isLoading: false});

    const eventSource = new EventSource('http://localhost:8888/sse/teams'); 
    eventSource.onopen = (event) => console.log('open', event); 
    eventSource.onmessage = (event) => {
      const profile = JSON.parse(event.data).source; 
      this.state.teams.push(profile);
      this.setState({teams: this.state.teams}); 
    };
    eventSource.onerror = (event) => console.log('error', event);

  }

  onTeamNameChange(e){
      this.setState({
        teamName: e.target.value
      });
  }
  
  addTeam(e){
    let data = {
      "name": this.state.teamName
    }
    const requestOptions = {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(data)
  };
      fetch('http://localhost:8888/teams/', requestOptions).then(response => { console.log(response)});
  }


  render() {
    const {teams, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
      <div>
        <input type="text" value={this.state.teamName} onChange={this.onTeamNameChange}></input> 
        <button onClick={this.addTeam}>Add Team</button>
        <h2>Profile List</h2>

        {teams.map((team) =>
          <div 
          // key={profile.id}
          >
            {team.name}<br/>
          </div>
        )}
      </div>
    );
  }
}

export default TeamList;