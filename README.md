# RandomGrowth

[![SpigotMC 1.14.4](https://img.shields.io/badge/SpigotMC-1.14.4-brightgreen.svg)](https://www.spigotmc.org/wiki/spigot/)
[![GitHub release](https://img.shields.io/github/release/frklan/RandomGrowth.svg)](https://github.com/frklan/RandomGrowth/releases)
[![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/frklan/RandomGrowth/issues)
[![License](https://img.shields.io/badge/License-CC%20BY--NC--SA-blue)](https://github.com/frklan/RandomGrowth/blob/master/LICENSE)

A (now a bit less) hacky way to get a bit more randomness in Bamboo plants in Minecraft

All Bamboo growth events are monitored and canceled when the plant reaches a random height between 3 and 16 blocks, we'll also set the BlockData parameter Stage is set to '1' and Leaves are set to 'LARGE' on the topmost bamboo block. Setting Stage to '1' prohibits further growth.

**Note** The server will throw an java.lang.IllegalArgumentException if we spam the bamboo plant with bone meal; unless I misunderstand the traceback and the Minecraft server source code; the bone meal interaction is handled asynchronously and sometimes the action is trying to grow the block we just canceled. Is this a bug or what am I missing?  

Be aware that the plugin might trash your server..

## Compiling

### Prerequisites

* Java 8 SDK
* Apache Maven

### Compiling

To compile issue the following commands

````bash
git clone git@github.com:frklan/RandomGrowth.git
cd RandomGrowth
mvn RandomGrowth
````

## Running

### Prerequisites

* Java 8
* A [SpigotMC](https://www.spigotmc.org/wiki/spigot/) server

### Installing

Compile and copy the jar file to your server's plugin folder and restart the server.

## Permissions

Since the plugin does not offer any player interactions, there is permission settings.

## Plugin Stats

No telemtry specific to this plugin is sent to anyone.

## Contributing

Contributions are always welcome!

When contributing to this repository, please first discuss the change you wish to make via the issue tracker, email, or any other method with the owner of this repository before making a change.

Please note that we have a code of conduct, you are required to follow it in all your interactions with the project.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/frklan/[TBD]/tags).

## Authors

* **Fredrik Andersson** - *Initial work* - [frklan](https://github.com/frklan)

## License

This project is licensed under the CC BY-NC-SA License - see the [LICENSE](LICENSE) file for details.
