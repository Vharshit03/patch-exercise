
## Summary of Changes

After running both the frontend and backend, I reviewed the codebase to understand how the application works.

I fixed an issue in taskRepository where task status changes were not being reflected correctly in search results. I updated the query condition from AND to OR so the updated status is returned as expected.

In taskController, I replaced fetching all tasks and filtering them in the application with pagination using limit and offset. Now, only the tasks needed for the requested page are fetched, reducing unnecessary data transfer and processing. I also used a database count query for pagination totals, which is more efficient than loading every task.

I added a 500 ms debounce to the search bar. Previously, it sent an API request for every keystroke; now it waits until the user pauses typing, reducing unnecessary requests.

## What I Chose Not to Change

I did not change the UI because it already looks clear and usable. I focused on performance issues that could affect user experience.

## Biggest Remaining Risk

The biggest remaining risk is in taskController around line 35, where each request deliberately blocks the server thread. This could make the API slow or unavailable when multiple users send requests.

## Tools/AI Used

I used ChatGPT to help draft the SQL query and understand the debounce logic, then reviewed and adapted the changes for this codebase.