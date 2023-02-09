import './App.css';
import React, { useState } from 'react';

interface Response {
  message: string;
  content: {
    pair: {
      id: string,
      eid1: string,
      eid2: string,
    },
    projects: {
      projects: Array<{
        projectId: string,
        period: number,
      }>,
      totalCollaborationDays: number,
    },
  };
}

const App = () => {
  const [response, setResponse] = useState<Response | null>(null);

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const file = (e.target as any).file.files[0];
    console.log(file);
    const formData = new FormData();
    formData.append('file', file);

    try {
      const res = await fetch('http://localhost:8081/api/v1/parse-csv', {
        method: 'POST',
        body: formData,
      });
      const json = await res.json();
      setResponse(json);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="h-100 text-center">
      <div className="container py-5">
        <div className="row">
          <div className="col-6 mx-auto">
            <h1 className="text-center">Employees</h1>
            <form onSubmit={handleSubmit}>
              <div className="form-group mt-4 ">
                <input
                  type="file"
                  name="file"
                  className="form-control-file custom-width2"
                  id="file"
                  accept=".csv"
                />
              </div>
              <div className="mt-2 text-center">
                <button type="submit" className="btn btn-primary mr-2 text-center
                custom-width">
                  Import Employees
                </button>
              </div>
            </form>
            {response && (

              <div className="mt-5">
                <h3 className="text-center">Biggest Collaborators</h3>
                <div className="table-responsive mt-3">
                  <table className="table">
                    <thead>
                      <tr>
                        <th>UserId1</th>
                        <th>UserId2</th>
                        <th>ProjectId</th>
                        <th>Period</th>
                      </tr>
                    </thead>
                    <tbody>
                      {response.content.projects.projects.map((project: any) => (
                        <tr key={project.projectId}>
                          <td>{response.content.pair.eid1}</td>
                          <td>{response.content.pair.eid2}</td>
                          <td>{project.projectId}</td>
                          <td>{project.period}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
                <h5 className="text-center mt-3">Total Collaboration Days: {response.content.projects.totalCollaborationDays}</h5>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default App;