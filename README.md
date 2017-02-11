# UniqueIDPlayer
An unique ID player, who use several mechanisms to generate unique ID 
( Some maybe only unique in a limited scope of time ).

## Why need Unique ID?
* Unique IDs are required by nearly all distributed systems. 
* There are so many mechanisms, which you can play with. In this repo, we use a uniform API and multi-implementations, you can choose and switch any of them as you wish.

## What id-generation mechanisms does UniqueIDPlayer provide?
* coming soon

## How to use
* Get the `UniqueIDPlayer` instance, and get ids by `nextStringId()` or `nextLongId()`.
```java
UniqueIDPlayer player = UniqueIDPlayer.newBuilder().find("IDGeneratorName");
```

* Use named `uniqueid.player.config` config file, and use default `find()`
```java
UniqueIDPlayer player = UniqueIDPlayer.newBuilder().find();
```
__see sample config file at [here](src/test/resources/uniqueid.player.config)

## Contributor
* [@wu-sheng](https://github.com/wu-sheng)
