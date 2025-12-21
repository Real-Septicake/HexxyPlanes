# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## 0.2.1

### Changed

- zh_cn translation updated by [ChuijkYahus](https://github.com/ChuijkYahus) via [#4](https://github.com/Real-Septicake/HexxyPlanes/pull/4)

## 0.2.0

### Fixed

- Commands now abides by permissions
- Planar Intrusion now respects Hex Casting's `cannot_teleport` tag
- Hexplane Iota now properly colors owner name

### Added

- A pattern for showing your hexplane exit (Reveal Mark)
- The ability to reset your hexplane exit by passing null to Planar Marking
- Interop for Oneironaut allowing for the retrieval of information regarding your hexplane exit
  - Oneironaut needed for providing Dimensional Imprint iota

## 0.1.1

### Fixed

- Ambit not correctly matching the size of the plane.

### Changed

- Ambit now includes the blocks making up the walls of the plane, allowing for raycast spells to target them.
  - They are still considered outside the world, however, preventing interaction with them.

## 0.1.0

### Added

- Initial version.
