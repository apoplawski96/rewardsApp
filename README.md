# Future Mind Take-Home Task

This repo contains a simplistic version of a loyalty program app. User collects points through
purchases and there are several rewards, which he can activate to claim them at the counter.
Activating a reward decreases his points balance.

## Task

The user stories to implement are:

1. As a user, when I click on an inactive reward, I want to activate it.
2. As a user, when I click on an active reward, I want to deactivate it.
3. As a user, when I activate or deactivate a reward, I want to see how my points balance has changed.

**We ask you to implement these user stories in a way that you would do it in a regular,
production-ready application using your best judgement when it comes to architecture, patterns,
development practices, optimal user experience etc.**

### Figma mockups

https://www.figma.com/file/dk9YXjwT5gPset1bDiJrH0/Task

### What will you find in this repository

To save your time, the skeleton for this function is already implemented.  
There's a view layer stub and there's a `RewardsApi` (which simulates regular remote API).
What is left for you to fill is the connection between the view layer and `RewardsApi` as well as
making sure that the app looks like the provided design and behaves in a user-friendly manner.

### Other info

- the skeleton is for your convenience, if you want to implement it differently, go ahead, just
  be prepared to talk about the solution you chose
- you can use different libraries, e.g. if you are more familiar with RxJava than coroutines,
  you're encouraged to use them
- the skeleton contains two versions of views on branches - Compose and Data Binding - choose the
  one you're more comfortable with
- if you want to provide additional info about your solution in the readme, feel free to do so

### Mock API

The `RewardsApi` is a mock that simulates a regular Retrofit-driven suspending API interface.
Just like the original, it might throw exceptions when something goes wrong. You should expect:

- `MockHttpException` with code 400 or 404 when there's something wrong with the request,
- `MockHttpException` with code 500 from time to time,
- `MockIoException` from time to time.

## Solution

To send the solved task, please create a private repository on bitbucket and share it with:
`a.klamborowski@futuremind.com`, `j.wisniewski@futuremind.com`, `m.klimczak@futuremind.com`, `t.jurek@futuremind.com`.
Also, please put the built apk in the repo. If you have any questions, send them to the addresses above.