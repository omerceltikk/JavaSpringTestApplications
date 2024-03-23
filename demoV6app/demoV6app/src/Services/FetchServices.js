export const FetchPostMethod = (url,body) => {
  const request =   fetch(`http://localhost:8080/${url}`,{
        method: 'POST',
        mode: "cors",
        headers: {
          'Accept': 'application/json, text/plain, */*',
          'Content-Type': 'application/json',
          "Authorization": localStorage.getItem("accessToken")
        },
        body:JSON.stringify(body)
    })
    return request
}
export const FetchGetMethod = async (url) => {
   const getUrl = await fetch(`http://localhost:8080/${url}?`)
    .then((res) => res.json())
    .catch((e) => {
        console.log(e)
    })
     return getUrl;
}
export const FetchDeleteMethod =async (url ,id) => {
 await fetch(`http://localhost:8080/${url}/${id}`,{
        method: 'DELETE',
        mode: "cors",
        headers: {
          'Accept': 'application/json, text/plain, */*',
          'Content-Type': 'application/json',
          "Authorization": "Bearer " + localStorage.getItem("accessToken")

        }
    }).catch((e) => console.log(e))
}
export const FetchPutMethod = async(url,body) => {
     await fetch(`http://localhost:8080/${url}`,{
        method: 'PUT',
        mode: "cors",
        headers: {
          'Accept': 'application/json, text/plain, */*',
          'Content-Type': 'application/json',
          "Authorization": localStorage.getItem("accessToken")

        },
        body:JSON.stringify(body)
    }).then(res => res.json()).then(res => console.log(res));
}

export const RefreshToken = () => {
 const request = fetch(`http://localhost:8080/auth/refresh`,{
    method: 'POST',
    mode: "cors",
    headers: {
      'Content-Type': 'application/json',
    },
    body:JSON.stringify({
      userId: localStorage.getItem("userId"),
      refreshToken:localStorage.getItem("refreshToken")
    })
})
return request;
}