import React, { useEffect, useState } from 'react'
import { FetchGetMethod } from '../Services/FetchServices'
import { Link, useParams } from 'react-router-dom';

const User = () => {
  const [activity, setActivity] = useState([]);
  let { userId } = useParams();
  const getAllActivityData = async () => {
    const response = await FetchGetMethod(`posts/activity/${userId}`)
    await setActivity(response);
  }

  useEffect(() => {
    getAllActivityData();
  }, [])
  return (
    <div className='bg-primary fill pt-5'>
      <div className="container pt-5">
        <div className="row g-5">
          <div className="col-6">
            <div className="card">
              <img src="..." className="card-img-top" alt="..." />
              <div className="card-body">
                <h5 className="card-title">Card title</h5>
                <p className="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
              </div>
            </div>
          </div>
          <div className="col-6">
            <ul className="list-group text-center">
              {
                activity?.length == 0 ? <li className='list-group-item'>You have not any activites.</li> :
                  activity.map((item) => (
                    <li className='list-group-item' key={item.id}>{item.userName} is {item.message} your <Link to={`posts/${item.postId}`}>post</Link></li>
                  ))
              }
            </ul>
          </div>
        </div>
      </div>
    </div>
  )
}

export default User