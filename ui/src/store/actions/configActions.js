import axios from "axios";



// ()=>({
//   sasas:"2"
// })

// export function fetchPosts() {
//   return dispatch => {
//     return API.fetchPosts().then(posts => {
//       dispatch({
//         type: FETCH_POSTS_SUCCESS,
//         posts
//       });

//       // fetch comments for each post
//       posts.forEach(post => {
//         dispatch(bindComments(post.id));
//       });
//     }).catch(error => {
//       dispatch({
//         type: FETCH_POSTS_FAILURE,
//         error
//       })
//     })
//   }
// }

export const getConfig  = () => dispatch => {
  axios.get("/config").then((res)=>{
    const config = { ...res.data, timestamp: new Date() };
    dispatch({
      type: "SET_CONFIG",
      payload: config,
    })
  })

};