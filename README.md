# Hair Salon

##### Epicodus exercise Database Basics code review, 02.29.2016

##### James Turello

## Description
This is an app for a hair salon. The owner can add a list of the stylists, and for each stylist, add clients who see that stylist. The stylists work independently, so each client only belongs to a single stylist.

## Setup

Clone this repository:

```
$ cd ~/Desktop
$ git clone https://github.com/jturello/hair_salon.git
$ cd hair_salon
```

Open terminal and run Postgres:

```
$ postgres
```

open file 'hair_salon.sql' in the root directory and change any statements 'owner=jturello' to owner=<your psql owner id>

Then in another terminal window enter the following commands:
* cd into the root directory (cd ~/Desktop/hair_salon)
* psql
* CREATE DATABASE hair_salon;
* hair_salon < hair_salon.sql;
  (you should see a bunch of sql DML commands printed to the terminal window)
* once the database is imported you can connect to it to run sql commands from the terminal by entering: psql
* \c hair_salon;

Then enter the following commands if you want to run the unit and integration tests against the project:
* CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;

To manually create the databases, enter the following commands in the terminal window from the project's root directory:

```
psql
$ CREATE DATABASE hair_salon;
$ \c hair_salon;
$ CREATE TABLE stylists (id serial PRIMARY KEY, name VARCHAR);
$ CREATE TABLE clients (id serial PRIMARY KEY, name VARCHAR, stylist_id REFERNECES stylists(id));
$ CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;
```



Navigate back to the directory where this repository has been cloned and run gradle:

To run and access the site enter these commands in a terminal window from the project root directory:

```
$ gradle run
```

Then in your browser enter url:

```
localhost:4567
```

## Legal

Copyright (c) 2016, James Turello

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
