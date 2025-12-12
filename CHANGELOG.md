# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## 0.1.2

### Fixed

- Commands did not abide by permissions
- Planar Intrusion now respects Hex Casting's `cannot_teleport` tag

### Added

- A pattern for showing your hexplane exit (Reveal Mark)
- The ability to reset your hexplane exit by passing null to Planar Marking

## 0.1.1

### Fixed

- Ambit not correctly matching the size of the plane.

### Changed

- Ambit now includes the blocks making up the walls of the plane, allowing for raycast spells to target them.
  - They are still considered outside the world, however, preventing interaction with them.

## 0.1.0

### Added

- Initial version.
